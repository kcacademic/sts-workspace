package com.ethereal.learning.aggregates;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.domain.AbstractAggregateRoot;

import com.ethereal.learning.events.DomainEvent;

@Entity
public class Aggregate extends AbstractAggregateRoot<Aggregate> {
	@Id
	@GeneratedValue
	private long id;

	public void domainOperation() {
		// some domain operation
		registerEvent(new DomainEvent());
	}

}