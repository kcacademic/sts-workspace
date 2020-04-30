package com.sapient.learning.events;

import lombok.Data;

@Data
public class User implements Aggregate {
	
	private String id;
	private String name;
	private String contact;
	
	public void process(CreateUserEvent event) {
		this.id = event.getId();
	}
	
	public void process(AddNameEvent event) {
		this.name = event.getName();
	}
	
	public void process(AddContactEvent event) {
		this.contact = event.getContact();
	}

}
