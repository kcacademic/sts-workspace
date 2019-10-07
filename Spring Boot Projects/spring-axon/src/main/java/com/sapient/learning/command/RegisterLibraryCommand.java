package com.sapient.learning.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class RegisterLibraryCommand {
	@TargetAggregateIdentifier
	private final Integer libraryId;

	private final String name;

	public RegisterLibraryCommand(Integer libraryId, String name) {
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