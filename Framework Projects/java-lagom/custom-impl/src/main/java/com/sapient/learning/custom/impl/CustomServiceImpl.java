package com.sapient.learning.custom.impl;

import static akka.pattern.Patterns.ask;
import static java.util.concurrent.CompletableFuture.completedFuture;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.sapient.learning.api.akka.Job;
import com.sapient.learning.api.akka.JobAccepted;
import com.sapient.learning.custom.akka.Worker;
import com.sapient.learning.custom.api.CustomService;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.cluster.Cluster;
import akka.cluster.routing.ClusterRouterGroup;
import akka.cluster.routing.ClusterRouterGroupSettings;
import akka.routing.ConsistentHashingGroup;

public class CustomServiceImpl implements CustomService {

	private final ActorRef workerRouter;
	
	private final ActorRef simpleWorker;

	@Inject
	public CustomServiceImpl(ActorSystem system) {
		if (Cluster.get(system).getSelfRoles().contains("worker-node")) {
			// start a worker actor on each node that has the "worker-node" role
			system.actorOf(Worker.props(), "worker");
		}
		
		// start a consistent hashing group router,
		// which will delegate jobs to the workers. It is grouping
		// the jobs by their task, i.e. jobs with same task will be
		// delegated to same worker node
		List<String> paths = Arrays.asList("/user/worker");
		ConsistentHashingGroup groupConf = new ConsistentHashingGroup(paths).withHashMapper(msg -> {
			if (msg instanceof Job) {
				return ((Job) msg).getTask();
			} else {
				return null;
			}
		});
		Set<String> useRoles = new TreeSet<>();
		useRoles.add("worker-node");

		Props routerProps = new ClusterRouterGroup(groupConf,
				new ClusterRouterGroupSettings(1000, paths, true, useRoles)).props();
		this.workerRouter = system.actorOf(routerProps, "workerRouter");
		
		this.simpleWorker = system.actorOf(Worker.props(), "myworker");
	}

	@Override
	public ServiceCall<NotUsed, String> custom(String id) {
		return request -> {
			System.out.println("Custom API Request: " + request);
			return completedFuture("Dummy response from my custom API for " + id);
		};
	}

	@Override
	public ServiceCall<Job, JobAccepted> doWork() {
		return job -> {
			System.out.println("DoWork invoked for the Job: " + job);
			System.out.println("Worker Router: " + workerRouter);
			// send the job to a worker, via the consistent hashing router
			ask(workerRouter, job, Duration.ofSeconds(5)).thenApply(ack -> {
				return (JobAccepted) ack;
			});
			
			// send the job to a worker, via the simple worker
			CompletionStage<JobAccepted> reply = ask(simpleWorker, job, Duration.ofSeconds(5)).thenApply(ack -> {
				return (JobAccepted) ack;
			});
			return reply;
		};
	}
}
