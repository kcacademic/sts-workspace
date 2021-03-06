package com.ethereal.learning.aggregate;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import com.ethereal.learning.command.RegisterBookCommand;
import com.ethereal.learning.command.RegisterLibraryCommand;
import com.ethereal.learning.event.BookCreatedEvent;
import com.ethereal.learning.event.LibraryCreatedEvent;

@Aggregate
public class Library {

	@AggregateIdentifier
	private Integer libraryId;
	private String name;
	private List<String> isbnBooks;

	protected Library() {
		// For Axon instantiation
	}

	@CommandHandler
	public Library(RegisterLibraryCommand cmd) {
		Assert.notNull(cmd.getLibraryId(), "ID should not be null");
		Assert.notNull(cmd.getName(), "Name should not be null");

		AggregateLifecycle.apply(new LibraryCreatedEvent(cmd.getLibraryId(), cmd.getName()));
	}

	@EventSourcingHandler
	private void handleCreatedEvent(LibraryCreatedEvent event) {
		libraryId = event.getLibraryId();
		name = event.getName();
		isbnBooks = new ArrayList<>();
	}
	
	@CommandHandler
	public void addBook(RegisterBookCommand cmd) {
		Assert.notNull(cmd.getLibraryId(), "ID should not be null");
		Assert.notNull(cmd.getIsbn(), "Book ISBN should not be null");

		AggregateLifecycle.apply(new BookCreatedEvent(cmd.getLibraryId(), cmd.getIsbn(), cmd.getTitle()));
	}

	@EventSourcingHandler
	private void addBook(BookCreatedEvent event) {
		isbnBooks.add(event.getIsbn());
	}

	public Integer getLibraryId() {
		return libraryId;
	}

	public String getName() {
		return name;
	}

	public List<String> getIsbnBooks() {
		return isbnBooks;
	}

}
