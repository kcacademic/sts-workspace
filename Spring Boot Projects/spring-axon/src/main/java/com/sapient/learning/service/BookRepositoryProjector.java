package com.sapient.learning.service;

import java.util.List;
import java.util.stream.Collectors;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import com.sapient.learning.domain.BookEntity;
import com.sapient.learning.event.BookCreatedEvent;
import com.sapient.learning.query.GetBooksQuery;
import com.sapient.learning.repository.BookRepository;
import com.sapient.learning.vo.BookBean;

@Service
public class BookRepositoryProjector {

	private final BookRepository bookRepository;

	public BookRepositoryProjector(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@EventHandler
	public void addBook(BookCreatedEvent event) throws Exception {
		BookEntity book = new BookEntity();
		book.setIsbn(event.getIsbn());
		book.setLibraryId(event.getLibraryId());
		book.setTitle(event.getTitle());
		bookRepository.save(book);
	}

	@QueryHandler
	public List<BookBean> getBooks(GetBooksQuery query) {
		return bookRepository.findByLibraryId(query.getLibraryId()).stream().map(e -> {
			BookBean book = new BookBean();
			book.setIsbn(e.getIsbn());
			book.setTitle(e.getTitle());
			return book;
		}).collect(Collectors.toList());
	}
}