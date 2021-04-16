package com.ethereal.learning.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class RegisterBookCommand {
	@TargetAggregateIdentifier
	private final Integer libraryId;
	private final String isbn;
	private final String title;

	public RegisterBookCommand(Integer libraryId, String isbn, String title) {
		super();
		this.libraryId = libraryId;
		this.isbn = isbn;
		this.title = title;
	}

	public Integer getLibraryId() {
		return libraryId;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

}