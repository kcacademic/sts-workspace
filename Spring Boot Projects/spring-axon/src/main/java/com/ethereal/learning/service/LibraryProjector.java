package com.ethereal.learning.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.axonframework.modelling.command.Repository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import com.ethereal.learning.aggregate.Library;
import com.ethereal.learning.query.GetLibraryQuery;

@Service
public class LibraryProjector {
	private final Repository<Library> libraryRepository;

	public LibraryProjector(Repository<Library> libraryRepository) {
		this.libraryRepository = libraryRepository;
	}

	@QueryHandler
	public Library getLibrary(GetLibraryQuery query) throws InterruptedException, ExecutionException {
		CompletableFuture<Library> future = new CompletableFuture<Library>();
		libraryRepository.load("" + query.getLibraryId()).execute(future::complete);
		return future.get();
	}

}
