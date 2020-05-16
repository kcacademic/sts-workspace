package com.sapient.learning.repositories;

import org.springframework.data.repository.CrudRepository;

import com.sapient.learning.aggregates.Aggregate;

public interface AggregateRepository extends CrudRepository<Aggregate, Long> {

}
