package com.sapient.learning.custom.impl;

import static akka.pattern.Patterns.ask;
import static java.util.concurrent.CompletableFuture.completedFuture;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.inject.Named;

import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.pubsub.PubSubRef;
import com.lightbend.lagom.javadsl.pubsub.PubSubRegistry;
import com.lightbend.lagom.javadsl.pubsub.TopicId;
import com.sapient.learning.api.akka.Job;
import com.sapient.learning.api.akka.JobAccepted;
import com.sapient.learning.api.akka.JobStatus;
import com.sapient.learning.custom.akka.Worker;
import com.sapient.learning.custom.api.CustomService;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.stream.javadsl.Source;

public class CustomServiceImpl implements CustomService {

	private final ActorRef workerRouter;

	private final ActorRef simpleWorker;

	private final PubSubRef<JobStatus> topic;

	@Inject
	public CustomServiceImpl(ActorSystem system, PubSubRegistry pubSub, @Named("worker") ActorRef worker) {

		this.workerRouter = worker;

		this.simpleWorker = system.actorOf(Worker.props(pubSub), "myworker");

		this.topic = pubSub.refFor(TopicId.of(JobStatus.class, "jobs-status"));
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
			CompletionStage<JobAccepted> reply = ask(workerRouter, job, Duration.ofSeconds(5)).thenApply(ack -> {
				return (JobAccepted) ack;
			});

			// send the job to a worker, via the simple worker
			ask(simpleWorker, job, Duration.ofSeconds(5)).thenApply(ack -> {
				return (JobAccepted) ack;
			});
			return reply;
		};
	}

	@Override
	public ServiceCall<NotUsed, Source<JobStatus, ?>> status() {
		return req -> {
			return CompletableFuture.completedFuture(topic.subscriber());
		};
	}
}
