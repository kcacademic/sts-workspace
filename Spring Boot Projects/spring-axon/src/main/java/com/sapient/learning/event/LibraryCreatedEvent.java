package com.sapient.learning.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class LibraryCreatedEvent {
	@TargetAggregateIdentifier
	private final Integer libraryId;
	private final String name;

	public LibraryCreatedEvent(Integer libraryId, String name) {
		super();
		this.libraryId = libraryId;
		this.name = name;
	}

	public Integer getLibraryId() {
		return libraryId;
	}

	public String getName() {
		return name;
	}

}
