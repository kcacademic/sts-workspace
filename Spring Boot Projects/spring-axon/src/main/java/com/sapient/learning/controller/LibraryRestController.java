package com.sapient.learning.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.learning.aggregate.Library;
import com.sapient.learning.command.RegisterBookCommand;
import com.sapient.learning.command.RegisterLibraryCommand;
import com.sapient.learning.query.GetBooksQuery;
import com.sapient.learning.query.GetLibraryQuery;
import com.sapient.learning.vo.BookBean;
import com.sapient.learning.vo.LibraryBean;

@RestController
public class LibraryRestController {

	private final CommandGateway commandGateway;
	private final QueryGateway queryGateway;

	@Autowired
	public LibraryRestController(CommandGateway commandGateway, QueryGateway queryGateway) {
		this.commandGateway = commandGateway;
		this.queryGateway = queryGateway;
	}

	@PostMapping("/api/library")
	public String addLibrary(@RequestBody LibraryBean library) {
		commandGateway.send(new RegisterLibraryCommand(library.getLibraryId(), library.getName()));
		return "Saved";
	}

	@GetMapping("/api/library/{library}")
	public Library getLibrary(@PathVariable Integer library) throws InterruptedException, ExecutionException {
		return queryGateway.query(new GetLibraryQuery(library), Library.class).get();
	}

	@PostMapping("/api/library/{library}/book")
	public String addBook(@PathVariable Integer library, @RequestBody BookBean book) {
		commandGateway.send(new RegisterBookCommand(library, book.getIsbn(), book.getTitle()),
				LoggingCallback.INSTANCE);
		return "Added";
	}

	@GetMapping("/api/library/{library}/book")
	public List<BookBean> addBook(@PathVariable Integer library) throws InterruptedException, ExecutionException {
		return queryGateway.query(new GetBooksQuery(library), ResponseTypes.multipleInstancesOf(BookBean.class)).get();
	}

}
