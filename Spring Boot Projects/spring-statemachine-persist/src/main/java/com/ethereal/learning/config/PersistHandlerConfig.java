package com.ethereal.learning.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler;

import com.ethereal.learning.persistence.Persister;

@Configuration
public class PersistHandlerConfig {

	@Autowired
	private StateMachine<String, String> stateMachine;

	@Bean
	public Persister persist() {
		return new Persister(persistStateMachineHandler());
	}

	@Bean
	public PersistStateMachineHandler persistStateMachineHandler() {
		return new PersistStateMachineHandler(stateMachine);
	}

}