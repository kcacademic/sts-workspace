package com.kchandrakant.learning.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.kchandrakant.learning.data.Message;

@Configuration
@EnableRabbit
@PropertySource(value = "classpath:application.properties")
public class AmqpConfig {

	@Value("${spring.rabbitmq.host}")
	private String host;

	@Value("${spring.rabbitmq.port}")
	private String port;

	@Value("${spring.rabbitmq.username}")
	private String username;

	@Value("${spring.rabbitmq.password}")
	private String password;

	@Bean
	public TopicExchange exchange() {
		return new TopicExchange("theExchange");
	}

	@Bean
	public Queue queue() {
		return new Queue("myQueue");
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("foo.*");
	}

	@Bean
	public RabbitAdmin admin() {
		return new RabbitAdmin(connectionFactory());
	}

	@Bean
	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {

		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory());
		factory.setMessageConverter(messageConverter());
		factory.setMaxConcurrentConsumers(5);

		return factory;
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {

		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setExchange("theExchange");
		template.setMessageConverter(messageConverter());

		return template;
	}

	@Bean
	public ConnectionFactory connectionFactory() {

		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost(host);
		connectionFactory.setPort(Integer.parseInt(port));
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);

		return connectionFactory;
	}

	@Bean
	public MessageConverter messageConverter() {

		Jackson2JsonMessageConverter jsonMessageConverter = new Jackson2JsonMessageConverter();
		jsonMessageConverter.setClassMapper(classMapper());
		return jsonMessageConverter;
	}

	@Bean
	public DefaultClassMapper classMapper() {

		DefaultClassMapper classMapper = new DefaultClassMapper();
		Map<String, Class<?>> idClassMapping = new HashMap<>();
		idClassMapping.put("com.kchandrakant.learning.data.Message", Message.class);
		classMapper.setIdClassMapping(idClassMapping);
		return classMapper;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
