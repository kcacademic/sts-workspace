package com.sapient.learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sapient.learning.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	Person findByUsername(String username);
}