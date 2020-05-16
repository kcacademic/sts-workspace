package com.sapient.learning.aggregates;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.sapient.learning.Application;
import com.sapient.learning.events.DomainEvent;
import com.sapient.learning.repositories.AggregateRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {Application.class})
public class AggregateEventsTests {

	@MockBean
	private TestEventHandler eventHandler;
	
	@Autowired
	private AggregateRepository repository;

	@Test
	public void afterDomainEvents() {
		// given
		Aggregate aggregate = new Aggregate();

		// when
		aggregate.domainOperation();
		repository.save(aggregate);
		repository.save(aggregate);

		// then
		verify(eventHandler, times(1)).handleEvent(any(DomainEvent.class));
	}

	@Test
	public void domainEvents() {
		// given
		Aggregate aggregate = new Aggregate();

		// when
		aggregate.domainOperation();
		repository.save(aggregate);

		// then
		verify(eventHandler, times(1)).handleEvent(any(DomainEvent.class));
	}

}
