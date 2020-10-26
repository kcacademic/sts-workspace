package com.baeldung.springboot.config;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "value.from")
public class SpringBootConfig {

	@Value("${value.from.string}")
	private String valueFromString;

	@Autowired
	Map<String, List<String>> anotherMap;

	private Map<String, List<String>> map;

	public String getValueFromString() {
		return valueFromString;
	}

	public Map<String, List<String>> getMap() {
		return map;
	}

	public void setMap(Map<String, List<String>> map) {
		this.map = map;
	}

	public Map<String, List<String>> getAnotherMap() {
		return anotherMap;
	}

}
