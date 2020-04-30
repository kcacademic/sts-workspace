package com.sapient.learning.events;

import java.util.List;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		EventStore store = new EventStore();

		CreateUserEvent createUserEvent = new CreateUserEvent("12345");
		store.addEvent(createUserEvent);
		Thread.sleep(1000);
		AddNameEvent addNameEvent = new AddNameEvent("Kumar Chandrakant");
		store.addEvent(addNameEvent);
		Thread.sleep(1000);
		AddContactEvent addContactEvent = new AddContactEvent("9871164118");
		store.addEvent(addContactEvent);

		List<Event> events = store.getEvents();
		User user = new User();
		for (Event event : events) {
			System.out.println(event.created);
			switch (event.getClass().getSimpleName()) {
				case "CreateUserEvent" :
					user.process((CreateUserEvent) event);
					break;
				case "AddNameEvent" :
					user.process((AddNameEvent) event);
					break;
				case "AddContactEvent" :
					user.process((AddContactEvent) event);
					break;
			}
		}
		System.out.println(user);
	}

}
