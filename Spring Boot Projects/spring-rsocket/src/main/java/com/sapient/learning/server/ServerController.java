package com.sapient.learning.server;

import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import reactor.core.publisher.Mono;

@Controller
public class ServerController {
 
    @MessageMapping("greeting")
    public Mono<String> currentMarketData(String request) {
        return Mono.just("Hello: " + request);
    }
	
	@MessageMapping
    public Mono<String> currentMarket(String request) {
        return Mono.just("Hello: " + request);
    }
    
    @MessageExceptionHandler
    public Mono<String> handleException(Exception e) {
        return Mono.just("Exception");
    }
}
