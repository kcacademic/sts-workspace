package com.ethereal.learning.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ethereal.learning.domain.BookEntity;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, String> {
	List<BookEntity> findByLibraryId(Integer libraryId);
}