package com.sapient.learning.aggregates;

import org.springframework.transaction.event.TransactionalEventListener;

import com.sapient.learning.events.DomainEvent;

interface TestEventHandler {
	@TransactionalEventListener
	void handleEvent(DomainEvent event);
}
