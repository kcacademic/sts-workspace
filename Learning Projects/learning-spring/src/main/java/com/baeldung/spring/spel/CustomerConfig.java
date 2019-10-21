package com.baeldung.spring.spel;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource (name = "customerProperties", value = "customer.properties")
@ConfigurationProperties
public class CustomerConfig {
    
    @Value ("#{'${customer.names}'.split(',')}")
    private List<String> customerNames;
    
    @Value ("#{${customer.age}}")
    private Map<String, Integer> customerAge;
    
    public List<String> getCustomerNames() {
        return customerNames;
    }

    public Map<String, Integer> getCustomerAge() {
        return customerAge;
    }
}
