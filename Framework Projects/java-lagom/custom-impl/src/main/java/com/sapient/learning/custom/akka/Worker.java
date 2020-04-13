package com.sapient.learning.custom.akka;

import com.sapient.learning.api.akka.Job;
import com.sapient.learning.api.akka.JobAccepted;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Worker extends AbstractActor {

	public static Props props() {
		return Props.create(Worker.class);
	}

	private final LoggingAdapter log = Logging.getLogger(context().system(), this);

	@Override
	public Receive createReceive() {
		return receiveBuilder().match(Job.class, this::perform)
				.matchAny(o -> System.out.println("Received unknown message")).build();
	}

	private void perform(Job job) {
		log.info("Working on job: {}", job);
		sender().tell(JobAccepted.of(job.getJobId()), self());
		// perform the work...
		context().stop(self());
	}
}
