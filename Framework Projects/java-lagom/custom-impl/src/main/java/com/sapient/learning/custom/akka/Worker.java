package com.sapient.learning.custom.akka;

import javax.inject.Inject;

import com.lightbend.lagom.javadsl.pubsub.PubSubRef;
import com.lightbend.lagom.javadsl.pubsub.PubSubRegistry;
import com.lightbend.lagom.javadsl.pubsub.TopicId;
import com.sapient.learning.api.akka.Job;
import com.sapient.learning.api.akka.JobAccepted;
import com.sapient.learning.api.akka.JobStatus;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Worker extends AbstractActor {

	private final LoggingAdapter log = Logging.getLogger(context().system(), this);

	private final PubSubRef<JobStatus> topic;

	public static Props props(PubSubRegistry pubSub) {
		return Props.create(Worker.class, pubSub);
	}

	@Inject
	public Worker(PubSubRegistry pubSub) {
		topic = pubSub.refFor(TopicId.of(JobStatus.class, "jobs-status"));
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder().match(Job.class, this::perform)
				.matchAny(o -> System.out.println("Received unknown message")).build();
	}

	private void perform(Job job) {
		log.info("Working on job: {}", job);
		sender().tell(JobAccepted.of(job.getJobId()), self());
		topic.publish(JobStatus.of(job.getJobId(), "started"));
		// perform the work...
		topic.publish(JobStatus.of(job.getJobId(), "completed"));
		// context().stop(self());
	}
}
