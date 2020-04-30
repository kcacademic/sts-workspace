package com.sapient.learning.events;

import java.util.ArrayList;
import java.util.List;

public class EventStore {
	
	private static List<Event> store = new ArrayList<>();
	
	public void addEvent(Event event) {
		store.add(event);
	}
	
	public List<Event> getEvents() {
		return store;
	}

}
