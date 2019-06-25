package com.sapient.learning.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.sapient.learning.model.Customer;

public interface CustomerRepository extends CassandraRepository<Customer, String> {

}