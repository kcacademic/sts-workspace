package com.sapient.learning;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineEventResult;

import com.sapient.learning.events.Events;
import com.sapient.learning.states.States;

import reactor.core.publisher.Flux;

public class Application {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.sapient.learning");

		@SuppressWarnings("unchecked")
		StateMachine<States, Events> stateMachine = context.getBean(StateMachine.class);

        stateMachine.startReactively().subscribe();
		
		Message<Events> message1 = MessageBuilder.withPayload(Events.EVENT1).build();
		Message<Events> message2 = MessageBuilder.withPayload(Events.EVENT2).build();

		Flux<StateMachineEventResult<States, Events>> results = stateMachine.sendEvents(Flux.just(message1, message2));

		results.subscribe();

		context.close();
	}

}