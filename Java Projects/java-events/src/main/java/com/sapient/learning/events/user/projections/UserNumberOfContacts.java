package com.sapient.learning.events.user.projections;

import com.sapient.learning.events.user.events.AddContactEvent;
import com.sapient.learning.events.user.events.AddNameEvent;
import com.sapient.learning.events.user.events.GenerateUserIdEvent;
import com.sapient.learning.events.user.events.DeleteContactEvent;
import com.sapient.learning.events.user.events.UpdateNameEvent;

import lombok.Data;

@Data
public class UserNumberOfContacts implements UserProjection {

	private String id;
	private int contacts;

	public void process(GenerateUserIdEvent event) {
		this.id = event.getUserid();
	}

	public void process(AddNameEvent event) {

	}

	public void process(UpdateNameEvent event) {

	}

	public void process(AddContactEvent event) {
		this.contacts++;
	}

	public void process(DeleteContactEvent event) {
		this.contacts--;
	}

}
