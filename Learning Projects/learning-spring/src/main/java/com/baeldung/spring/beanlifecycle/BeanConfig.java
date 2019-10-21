package com.baeldung.spring.beanlifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public MyBean mybean() {
        return new MyBean();
    }
}
