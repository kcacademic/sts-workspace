package com.sapient.learning.receiver;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.sapient.learning.data.Email;

@Component
public class MessageListener {
	
	@JmsListener(destination = "mailbox")
	public void receiveMessage(Email email) {
		System.out.println("Received <" + email + ">");
	}

}
