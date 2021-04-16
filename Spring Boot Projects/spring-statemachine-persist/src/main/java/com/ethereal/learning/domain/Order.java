package com.ethereal.learning.domain;

public class Order {
	int id;
	String state;

	public Order(int id, String state) {
		this.id = id;
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", state=" + state + "]";
	}

}