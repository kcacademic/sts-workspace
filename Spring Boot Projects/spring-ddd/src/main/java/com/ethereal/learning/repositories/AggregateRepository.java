package com.ethereal.learning.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ethereal.learning.aggregates.Aggregate;

public interface AggregateRepository extends CrudRepository<Aggregate, Long> {

}
