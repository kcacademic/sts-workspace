package com.sapient.learning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import com.sapient.learning.events.Events;
import com.sapient.learning.states.States;

@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {

	@Override
	public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {
		config.withConfiguration().autoStartup(true).listener(listener());
	}

	@Override
	public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
		states
			.withStates()
				.initial(States.SI, executeAction())
				.fork(States.SF)
				.join(States.SJ)
				.end(States.SE)
				.and()
					.withStates()
					.parent(States.SF)
					.initial(States.S1)
					.end(States.SE1)
				.and()
					.withStates()
					.parent(States.SF)
					.initial(States.S2)
					.end(States.SE2);
					
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
		transitions
			.withExternal()
				.source(States.SI).target(States.SF).event(Events.E1).action(simpleAction())
			.and().withFork()
				.source(States.SF).target(States.S1).target(States.S2)
			.and().withExternal()
				.source(States.S1).target(States.SE1).event(Events.E2)
			.and().withExternal()
				.source(States.S2).target(States.SE2).event(Events.E3).guard(simpleGuard())
			.and().withJoin()
				.source(States.SE1).source(States.SE2).target(States.SJ)
			.and().withExternal()
				.source(States.SJ).target(States.SE).event(Events.E4);
	}
	
	@Bean
	public Action<States, Events> simpleAction() {
	    return ctx -> System.out.println(ctx.getTarget().getId());
	}
	
	@Bean
	public Action<States, Events> executeAction() {
	    return ctx -> {
	        int approvals = (int) ctx.getExtendedState().getVariables()
	          .getOrDefault("approvalCount", 0);
	        approvals++;
	        ctx.getExtendedState().getVariables()
	          .put("approvalCount", approvals);
	    };
	}
	
	@Bean
	public Guard<States, Events> simpleGuard() {
	    return ctx -> (int) ctx.getExtendedState()
	      .getVariables()
	      .getOrDefault("approvalCount", 0) > 0;
	}
	
	@Bean
	public StateMachineListener<States, Events> listener() {
		return new StateMachineListenerAdapter<States, Events>() {
			@Override
			public void stateChanged(State<States, Events> from, State<States, Events> to) {
				System.out.println("State change to " + to.getId());
			}
		};
	}
}
