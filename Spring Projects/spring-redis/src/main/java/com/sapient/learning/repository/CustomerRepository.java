package com.sapient.learning.repository;

import org.springframework.data.repository.CrudRepository;

import com.sapient.learning.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, String> {

}