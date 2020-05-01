package com.sapient.learning.events.user;

import java.util.Arrays;
import java.util.List;

import com.sapient.learning.events.base.Event;
import com.sapient.learning.events.base.EventStore;
import com.sapient.learning.events.user.aggregates.UserAggregate;
import com.sapient.learning.events.user.commands.CreateUserCommand;
import com.sapient.learning.events.user.commands.UpdateUserCommand;
import com.sapient.learning.events.user.events.AddContactEvent;
import com.sapient.learning.events.user.events.AddNameEvent;
import com.sapient.learning.events.user.events.DeleteContactEvent;
import com.sapient.learning.events.user.events.GenerateUserIdEvent;
import com.sapient.learning.events.user.events.UpdateNameEvent;
import com.sapient.learning.events.user.projections.UserNumberOfContacts;
import com.sapient.learning.events.user.projections.UserSummary;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		EventStore repository = new EventStore();

		UserAggregate userAggregate = new UserAggregate(repository);
		List<String> contacts = Arrays.asList("9871164117", "9958522300");
		CreateUserCommand createUserCommand = new CreateUserCommand("Kumar",
				contacts);
		userAggregate.handle(createUserCommand);
		List<String> upadtedContacts = Arrays.asList("9871164118",
				"9958522300");
		UpdateUserCommand updateUserCommand = new UpdateUserCommand(
				"Kumar Chandrakant", upadtedContacts);
		userAggregate.handle(updateUserCommand);

		System.out.println(userAggregate);
		System.out.println((new UserAggregate(repository,
				userAggregate.getId().toString())));

		List<Event> events = repository
				.getEvents(userAggregate.getId().toString());
		UserSummary summary = new UserSummary();
		UserNumberOfContacts numberOfContacts = new UserNumberOfContacts();
		for (Event event : events) {
			System.out.println(event);
			if (event instanceof GenerateUserIdEvent) {
				summary.process((GenerateUserIdEvent) event);
				numberOfContacts.process((GenerateUserIdEvent) event);
			}
			if (event instanceof AddNameEvent) {
				summary.process((AddNameEvent) event);
				numberOfContacts.process((AddNameEvent) event);
			}
			if (event instanceof UpdateNameEvent) {
				summary.process((UpdateNameEvent) event);
				numberOfContacts.process((UpdateNameEvent) event);
			}
			if (event instanceof AddContactEvent) {
				summary.process((AddContactEvent) event);
				numberOfContacts.process((AddContactEvent) event);
			}
			if (event instanceof DeleteContactEvent) {
				summary.process((DeleteContactEvent) event);
				numberOfContacts.process((DeleteContactEvent) event);
			}
		}
		System.out.println(summary);
		System.out.println(numberOfContacts);
	}

}
