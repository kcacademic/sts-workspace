package com.sapient.learning.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

	@KafkaListener(topics = "${message.topic.name}")
	public void listen(String message) {
		System.out.println("Received Messasge: " + message);
	}

}
