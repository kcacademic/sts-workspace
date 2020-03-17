package com.sapient.learning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.learning.domain.Car;

import reactor.core.publisher.Flux;

@RestController
class ApplicationController {

    @GetMapping("/cars")
    public Flux<Car> getCars() {
        return Flux.empty();
    }
}
