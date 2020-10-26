package com.baeldung.spring.config;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("application.properties")
public class SpringConfig {

	@Value("${value.from.string}")
	private String valueFromString;

	@Value("#{${value.from.map}}")
	private Map<String, List<String>> valuesFromMap;

	public String getValueFromString() {
		return valueFromString;
	}

	public Map<String, List<String>> getValuesFromMap() {
		return valuesFromMap;
	}

}
