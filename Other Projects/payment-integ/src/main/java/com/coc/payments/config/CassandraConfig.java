package com.coc.payments.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.core.mapping.SimpleUserTypeResolver;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@PropertySource(value = "classpath:application.yml")
@EnableCassandraRepositories(basePackages = "com.coc.payments.repository")
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Value("${payments.cassandra.keyspace:coc_payments}")
    private String keySpace;

    @Value("${payments.cassandra.host:127.0.0.1}")
    private String host;

    @Value("${payments.cassandra.port:9042}")
    private int port;
    
    @Value("${payments.cassandra.username:cassandra}")
    private String username;

    @Value("${payments.cassandra.password:cassandra}")
    private String password;

    @Override
    protected String getKeyspaceName() {
        return keySpace;
    }

    @Bean
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints(host);
        cluster.setPort(port);
        cluster.setUsername(username);
        cluster.setPassword(password);
        return cluster;
    }

    @Bean
    public CassandraMappingContext cassandraMapping() throws ClassNotFoundException {
        CassandraMappingContext mappingContext = new CassandraMappingContext();
        mappingContext.setUserTypeResolver(new SimpleUserTypeResolver(cluster().getObject(), getKeyspaceName()));
        return mappingContext;
    }

}