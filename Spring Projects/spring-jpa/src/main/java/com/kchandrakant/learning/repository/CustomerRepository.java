package com.kchandrakant.learning.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kchandrakant.learning.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
}