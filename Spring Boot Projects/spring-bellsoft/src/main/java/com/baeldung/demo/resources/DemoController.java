package com.baeldung.demo.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class DemoController {

    @GetMapping("/api/v1/fibs")
    public List<Integer> getFibonacciSeriesBelowGivenInteger(Integer input) {
        List<Integer> result;
        if (input == 0)
            result = List.of(0);
        else {
            int n = 0;
            int m = 1;
            result = new ArrayList<>(Arrays.asList(n));
            while (m <= input) {
                result.add(m);
                m = n + m;
                n = m - n;
            }
        }
        return result;
    }
}
