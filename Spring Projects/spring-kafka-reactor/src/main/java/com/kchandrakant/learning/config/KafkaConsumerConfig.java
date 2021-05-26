package com.kchandrakant.learning.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

@Configuration
@PropertySource(value = "classpath:application.properties")
public class KafkaConsumerConfig {

	@Value(value = "${kafka.bootstrapAddress}")
	private String bootstrapAddress;

	@Bean
	public KafkaReceiver<String, String> kafkaReceiver() {

		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		props.put(ConsumerConfig.CLIENT_ID_CONFIG, "sample-consumer");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "sample-group");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		ReceiverOptions<String, String> receiverOptions = ReceiverOptions.create(props);

		receiverOptions.subscription(Collections.singleton("reactive-test"))
				.addAssignListener(partitions -> System.out.println("onPartitionsAssigned: " + partitions))
				.addRevokeListener(partitions -> System.out.println("onPartitionsRevoked: " + partitions));

		return KafkaReceiver.create(receiverOptions);

	}
}