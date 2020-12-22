package com.sapient.learning.domain;

import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

@WithStateMachine
public class MyBean {

	@OnTransition(target = "STATE1")
	public void toState1() {
		System.out.println("Transitioning to STATE1");
	}

	@OnTransition(target = "STATE2")
	public void toState2() {
		System.out.println("Transitioning to STATE2");
	}
}