package com.sapient.learning.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Address {

	public Address() {
	}

	public Address(String street, String town, String county, String postcode) {
		this.street = street;
		this.town = town;
		this.county = county;
		this.postcode = postcode;
	}

	@Getter
	private long id;

	@Setter
	@Getter
	private String street;

	@Setter
	@Getter
	private String town;

	@Setter
	@Getter
	private String county;

	@Setter
	@Getter
	private String postcode;
}