package com.kchandrakant.learning.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.kchandrakant.learning.model.Customer;

public interface CustomerRepository extends Neo4jRepository<Customer, String> {

}