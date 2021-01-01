package com.sapient.learning.config;

import java.util.EnumSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.ReactiveAction;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import com.sapient.learning.events.Events;
import com.sapient.learning.states.States;

import reactor.core.publisher.Mono;

@Configuration
@EnableStateMachine
public class StateMachineConfig extends StateMachineConfigurerAdapter<States, Events> {
	
    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config)
            throws Exception {
        config
            .withConfiguration()
                .autoStartup(true)
                .listener(myListener());
    }

	@Override
	public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
		states
			.withStates()
				.initial(States.STATE1)
				.states(EnumSet.allOf(States.class));
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
		transitions
			.withExternal()
				.source(States.STATE1).target(States.STATE2).event(Events.EVENT1).actionFunction(myAction())
			.and().withExternal()
				.source(States.STATE2).target(States.STATE1).event(Events.EVENT2);
	}
	
    @Bean
    public StateMachineListener<States, Events> myListener() {
        return new StateMachineListenerAdapter<States, Events>() {
            @Override
            public void stateChanged(State<States, Events> from, State<States, Events> to) {
                System.out.println("State change to " + to.getId());
            }
        };
    }
    
    @Bean
    public ReactiveAction<States, Events> myAction() {
		return new ReactiveAction<States, Events>() {

			public Mono<Void> apply(StateContext<States, Events> t) {
				System.out.println("My Action: " + t.getEvent());
				return Mono.empty();
			}
		};
    	
    }
	
}