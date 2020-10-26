package com.baeldung.springboot.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBootMapConfig {
	
	@Bean
	@ConfigurationProperties(prefix = "value.from.another-map")
	public Map<String, List<String>> anotherMap() {
		return new HashMap<>();
	}

}
