package com.kchandrakant.learning.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kchandrakant.learning.data.MyDao;

@Service
public class MyService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private MyDao dao;
    
    public String calculateSomething() {
        String value = dao.retrieveSomething();
        logger.info("In Business - {}", value);
        return value;
    }
}