package com.baeldung.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baeldung.spring.bean.MyBean;

@Configuration
public class BeanConfig {

    @Bean
    public MyBean mybean() {
        return new MyBean();
    }
}
