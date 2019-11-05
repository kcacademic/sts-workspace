package com.coc.payments.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.CassandraEntityClassScanner;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.convert.CassandraConverter;
import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.core.mapping.SimpleUserTypeResolver;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@PropertySource(value = "classpath:application.yml")
@EnableCassandraRepositories(basePackages = "com.coc.payments.repository")
public class CassandraConfig extends AbstractCassandraConfiguration {

    Logger logger = LoggerFactory.getLogger(CassandraConfig.class);

    @Value("${payments.cassandra.keyspace:coc_payments}")
    private String keySpace;

    @Value("${payments.cassandra.host:127.0.0.1}")
    private String host;

    @Value("${payments.cassandra.port:9042}")
    private int port;

    @Value("${payments.cassandra.username}")
    private String username;

    @Value("${payments.cassandra.password}")
    private String password;

    @Override
    protected String getKeyspaceName() {
        return keySpace;
    }

    @Bean
    @Override
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints(host);
        cluster.setPort(port);
        cluster.setUsername(username);
        cluster.setPassword(password);
        return cluster;
    }

    @Bean
    @Override
    public CassandraMappingContext cassandraMapping() {
        CassandraMappingContext mappingContext = new CassandraMappingContext();
        mappingContext.setUserTypeResolver(new SimpleUserTypeResolver(cluster().getObject(), getKeyspaceName()));
        mappingContext.setInitialEntitySet(getInitialEntitySet());
        return mappingContext;
    }

    @Override
    protected Set<Class<?>> getInitialEntitySet() {
        try {
            return CassandraEntityClassScanner.scan(getEntityBasePackages());
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
        return Collections.emptySet();
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        final CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.createKeyspace(getKeyspaceName())
            .ifNotExists()
            .with(KeyspaceOption.DURABLE_WRITES, true)
            .withSimpleReplication();
        return Arrays.asList(specification);
    }

    @Bean
    @Override
    public CassandraSessionFactoryBean session() {
        CassandraSessionFactoryBean session = new CassandraSessionFactoryBean();
        session.setCluster(cluster().getObject());
        session.setKeyspaceName(getKeyspaceName());
        session.setConverter(converter());
        session.setSchemaAction(SchemaAction.CREATE_IF_NOT_EXISTS);
        return session;
    }

    @Bean
    public CassandraConverter converter() {
        return new MappingCassandraConverter(cassandraMapping());
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[] { "com.coc.payments.domain" };
    }
}