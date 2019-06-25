package com.sapient.learning.controller;

import java.time.Duration;
import java.util.Calendar;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.learning.model.Tweet;
import com.sapient.learning.repository.TweetRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RestController
public class TweetController {

	@Autowired
	private TweetRepository tweetRepository;

	@GetMapping("/tweets")
	public Flux<Tweet> getAllTweets() {
		return tweetRepository.findAll();
	}

	@PostMapping("/tweets")
	public Mono<Tweet> createTweets(@Valid @RequestBody Tweet tweet) {
		return tweetRepository.save(tweet);
	}

	@GetMapping("/tweets/{id}")
	public Mono<ResponseEntity<Tweet>> getTweetById(@PathVariable(value = "id") String tweetId) {
		return tweetRepository.findById(tweetId).map(savedTweet -> ResponseEntity.ok(savedTweet))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@PutMapping("/tweets/{id}")
	public Mono<ResponseEntity<Tweet>> updateTweet(@PathVariable(value = "id") String tweetId,
			@Valid @RequestBody Tweet tweet) {
		return tweetRepository.findById(tweetId).flatMap(existingTweet -> {
			existingTweet.setText(tweet.getText());
			return tweetRepository.save(existingTweet);
		}).map(updatedTweet -> new ResponseEntity<>(updatedTweet, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("/tweets/{id}")
	public Mono<ResponseEntity<Void>> deleteTweet(@PathVariable(value = "id") String tweetId) {

		return tweetRepository.findById(tweetId)
				.flatMap(existingTweet -> tweetRepository.delete(existingTweet)
						.then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	// Tweets are Sent to the client as Server Sent Events
	@GetMapping(value = "/stream/tweets", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Tweet> streamAllTweets() {
		return tweetRepository.findAll();
	}
	
	
    @GetMapping("/messages/{id}")
    public Mono<String> getMessage(@PathVariable("id") long id) {
        return Mono.just("Hi " + id);
    }
    
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/messages")
    public Flux<String> getAllmessages() {
        final Flux<String> flux = Flux
        		.interval(Duration.ofMillis(1000))
        		.map((interval) -> {
        			return (new StringBuilder("Time at: ").append(interval).append(" : ").append(Calendar.getInstance().getTime()).toString());
        		});
        
        return flux;
    }
    
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/messages1")
    public Flux<String> getAllMessages1() {
        final Flux<String> messageFlux = Flux.fromStream(Stream.generate(() -> "Hi: "+Calendar.getInstance().getTime()));
        final Flux<Long> emmitFlux = Flux.interval(Duration.ofSeconds(1));
        return Flux.zip(messageFlux, emmitFlux).map(Tuple2::getT1).log();
    }
	
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/messages2")
    public Flux<String> getAllmessages2() {
        final Flux<String> flux = Flux.<String> create(fluxSink -> {
            while (true) {
                fluxSink.next("Hi: "+Calendar.getInstance().getTime());
            }
        }).sample(Duration.ofSeconds(1)).onBackpressureDrop().log();

        return flux;
    }
    

}