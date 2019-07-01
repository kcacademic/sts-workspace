package com.sapient.learning.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="purchase")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderId;
	private String address;
	private String payment;
    @ManyToOne
    @JoinColumn(name = "customerId")
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
		return String.format("Order [orderId=%d, address='%s', payment='%s']", orderId, address, payment);
	}

}
