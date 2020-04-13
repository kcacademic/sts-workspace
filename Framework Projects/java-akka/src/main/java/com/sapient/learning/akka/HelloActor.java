package com.sapient.learning.akka;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class HelloActor extends AbstractActor {

	private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

	public static Props props() {
		return Props.create(HelloActor.class);
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder().match(HelloMessage.class, o -> log.info("Received hello message: " + o.getMessage()))
				.matchAny(o -> log.error("Received unknown message")).build();
	}
}