package com.sapient.learning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PURCHASE")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ORDER_ID")
	private Long orderId;
	@Column(name = "ADDRESS")
	private String address;
	@Column(name = "PAYMENT")
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
