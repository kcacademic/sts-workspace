package com.baeldung.demo.resources;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DemoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_Generation_of_Fibonacci_Series_Below_a_Given_Integer() throws Exception {

        this.mockMvc
                .perform(get("/api/v1/fibs?input=5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[0,1,1,2,3,5]")));

    }
}
