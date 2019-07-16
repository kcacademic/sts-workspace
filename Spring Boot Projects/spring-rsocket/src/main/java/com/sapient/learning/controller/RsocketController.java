package com.sapient.learning.controller;

import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import reactor.core.publisher.Mono;

@Controller
public class RsocketController {
 
    @MessageMapping("greeting")
    public Mono<String> getGreeting(String request) {
        return Mono.just("Hello: " + request);
    }
    
    @MessageExceptionHandler
    public Mono<String> handleException(Exception e) {
        return Mono.just("Exception");
    }
}
