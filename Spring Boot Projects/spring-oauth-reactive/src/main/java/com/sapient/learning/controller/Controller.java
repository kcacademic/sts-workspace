package com.sapient.learning.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequestMapping("/api")
@RestController
public class Controller {

    @GetMapping("/index")
    public Mono<String> index(@AuthenticationPrincipal Jwt jwt) {
    	System.out.println(jwt.getClaims());
        return Mono.just(String.format("Hello, %s!", jwt.getClaimAsString("name")));
    }
    
    @GetMapping("/update")
    public Mono<String> update(@AuthenticationPrincipal Jwt jwt) {
    	System.out.println(jwt.getClaims());
        return Mono.just(String.format("Hello, %s!", jwt.getClaimAsString("name")));
    }

}
