package com.sapient.learning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.ConnectionFactoryOptions.Builder;

@Configuration
public class DatasourceConfig {

	@Bean
	public ConnectionFactory connectionFactory() {
		ConnectionFactoryOptions baseOptions = ConnectionFactoryOptions
				.parse("r2dbc:h2:mem://./testdb");
		Builder ob = ConnectionFactoryOptions.builder().from(baseOptions);
		return ConnectionFactories.get(ob.build());
	}

}
