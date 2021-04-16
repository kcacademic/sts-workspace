package com.ethereal.learning.repository;

import org.springframework.data.repository.CrudRepository;

import com.ethereal.learning.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}