package com.sapient.learning.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@PropertySource("classpath:persistence-h2.properties")
public class PersistenceConfig {

	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase dataSource = 
				builder
					.setType(EmbeddedDatabaseType.H2)
					.addScript("classpath:/persisted_logins_create_table.sql")
					.build();
		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "com.sapient.learning.domain" });
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		em.setJpaProperties(additionalProperties());
		return em;
	}

	final Properties additionalProperties() {
		final Properties hibernateProperties = new Properties();
		if (env.getProperty("hibernate.hbm2ddl.auto") != null) {
			hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		}
		if (env.getProperty("hibernate.dialect") != null) {
			hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		}
		if (env.getProperty("hibernate.show_sql") != null) {
			hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		}
		return hibernateProperties;
	}

}
