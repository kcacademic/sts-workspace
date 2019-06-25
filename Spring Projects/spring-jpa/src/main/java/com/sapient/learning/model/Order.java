package com.sapient.learning.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_table")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderId;
	private String address;
	private String payment;
	@OneToOne
	private Customer customer;

	protected Order() {
	}

	public Order(String address, String payment, Customer customer) {
		this.address = address;
		this.payment = payment;
		this.customer = customer;
	}

	@Override
	public String toString() {
		return String.format("Order [orderId=%d, address='%s', payment='%s, customer='%s']", orderId, address, payment, customer);
	}

}
