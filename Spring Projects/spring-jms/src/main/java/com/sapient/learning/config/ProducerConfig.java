package com.sapient.learning.config;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
@PropertySource(value = "classpath:application.properties")
public class ProducerConfig {

	@Value("${activemq.broker-url}")
	private String brokerUrl;
	
	@Value("${activemq.message-queue}")
	private String messageQueue;

	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(senderConnectionFactory());
		template.setDefaultDestinationName(messageQueue);
		template.setMessageConverter(producerMessageConverter());
		return template;
	}

	@Bean
	public ActiveMQConnectionFactory senderConnectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(brokerUrl);
		return connectionFactory;
	}

	@Bean
	public MessageConverter producerMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

}
