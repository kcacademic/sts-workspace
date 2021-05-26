package com.kchandrakant.learning.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.kchandrakant.learning.model.Customer;

public interface CustomerRepository extends CassandraRepository<Customer, String> {

}