package com.kchandrakant.learning.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.sapient.learning.User;

@Component
public class MessageListener {

	@KafkaListener(topics = "${message.topic.name}")
	public void listen(User user) {
		System.out.println("Received Messasge: " + user);
	}

}
