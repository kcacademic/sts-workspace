package com.sapient.learning.client;

import org.springframework.web.reactive.function.client.WebClient;

import com.sapient.learning.model.Tweet;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class TweetClient {

    WebClient client = WebClient.create("http://localhost:8080");
    
    public void consume() {

        Mono<Tweet> tweetMono = client.get()
            .uri("/tweets/{id}", "5b655f823de1833890e4ec29")
            .retrieve()
            .bodyToMono(Tweet.class);
        tweetMono.subscribe(System.out::println);
        Flux<Tweet> tweetFlux = client.get()
            .uri("/tweets")
            .retrieve()
            .bodyToFlux(Tweet.class);
        tweetFlux.subscribe(System.out::println);
        
        
        Flux<String> messageFlux = client.get()
            .uri("/messages")
            .retrieve()
            .bodyToFlux(String.class);
        messageFlux.subscribe(System.out::println);
    }
    
}