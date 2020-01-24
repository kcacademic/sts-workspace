package com.sapient.learning;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.statemachine.StateMachine;

import com.sapient.learning.events.Events;
import com.sapient.learning.states.States;

public class Application {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.sapient.learning");

		@SuppressWarnings("unchecked")
		StateMachine<States, Events> stateMachine = context.getBean(StateMachine.class);
		
		stateMachine.sendEvent(Events.E1);
		stateMachine.sendEvent(Events.E2);
		stateMachine.sendEvent(Events.E3);
		stateMachine.sendEvent(Events.E4);

		context.close();
	}

}