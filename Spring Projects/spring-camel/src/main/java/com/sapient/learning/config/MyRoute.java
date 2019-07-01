package com.sapient.learning.config;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MyRoute extends SpringRouteBuilder {

	@Override
	public void configure() throws Exception {
		from("file://data/input").process(getProcessor()).to("file://data/output/upper").transform()
				.simple("${body.toLowerCase()}").to("file://data/output/lower");

	}

	@Bean
	public Processor getProcessor() {
		return new Processor() {
			public void process(Exchange exchange) throws Exception {
				String originalFileContent = (String) exchange.getIn().getBody(String.class);
				String upperCaseFileContent = originalFileContent.toUpperCase();
				exchange.getIn().setBody(upperCaseFileContent);
			}
		};
	}

}
