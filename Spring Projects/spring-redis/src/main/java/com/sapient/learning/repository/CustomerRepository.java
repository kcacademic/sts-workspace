package com.sapient.learning.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sapient.learning.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, String> {

	public Customer findByFirstName(String firstName);

	public List<Customer> findByLastName(String lastName);

}