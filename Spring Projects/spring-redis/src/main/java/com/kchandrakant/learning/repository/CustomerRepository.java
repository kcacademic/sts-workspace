package com.kchandrakant.learning.repository;

import org.springframework.data.repository.CrudRepository;

import com.kchandrakant.learning.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, String> {

}