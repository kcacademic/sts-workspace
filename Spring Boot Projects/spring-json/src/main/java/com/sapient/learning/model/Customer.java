package com.sapient.learning.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Customer {

	public Customer() {
	}

	public Customer(String firstName, String lastName, LocalDate dateOfBirth, Address address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
	}

	@Getter
	private long customerId;

	@Setter
	@Getter
	private String firstName;

	@Setter
	@Getter
	private String lastName;

	@Setter
	@Getter
	private LocalDate dateOfBirth;

	@Setter
	@Getter
	private Address address;
}