package com.sapient.learning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.monitorjbl.json.JsonViewSupportFactoryBean;

@EnableWebMvc
@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Bean
	public JsonViewSupportFactoryBean views() {
		return new JsonViewSupportFactoryBean();
	}

}
