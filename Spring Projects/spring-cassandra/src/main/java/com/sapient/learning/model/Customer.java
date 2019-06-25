package com.sapient.learning.model;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;

public class Customer {

	@PrimaryKey
    private UUID id;

	public String firstName;
	public String lastName;

	public Customer() {
	}

	public Customer(String firstName, String lastName) {
		this.id = UUID.randomUUID();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return String.format("Customer[id=%s, firstName='%s', lastName='%s']", id, firstName, lastName);
	}

}