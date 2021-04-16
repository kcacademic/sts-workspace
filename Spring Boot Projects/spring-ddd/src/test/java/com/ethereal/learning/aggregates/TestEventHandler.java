package com.ethereal.learning.aggregates;

import org.springframework.transaction.event.TransactionalEventListener;

import com.ethereal.learning.events.DomainEvent;

interface TestEventHandler {
	@TransactionalEventListener
	void handleEvent(DomainEvent event);
}
