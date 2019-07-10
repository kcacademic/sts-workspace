package com.sapient.learning.service;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service
public class MyService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Async
    public Future<String> calculateSomething() throws InterruptedException {
    	
        logger.info("In Business - {}", "Kumar Chandrakant");
        Thread.sleep(1000);
        return new AsyncResult<String>("Kumar Chandrakant");
        
    }
}