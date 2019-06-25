package com.baeldung.spring.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.baeldung.spring.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EmployeeControllerTests {
    
    @Autowired
    private MockMvc mvc;
    
    @Test
    @WithMockUser()
    public void givenNoEmployee_whenCreateEmployee_thenEmployeeCreated() throws Exception {

        mvc.perform(post("/employees").content(new ObjectMapper().writeValueAsString(new Employee("Kumar", "Chandrakant")))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status()
                .isCreated())
            .andExpect(jsonPath("$.firstName", is("Kumar")))
            .andExpect(jsonPath("$.lastName", is("Chandrakant")));
    }

    @Test
    public void givenEmployees_whenGetEmployees_thenReturnEmployees() throws Exception {

        mvc.perform(get("/employees").accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @WithMockUser()
    public void givenEmployee_whenDeleteEmployee_thenEmployeeDeleted() throws Exception {

        mvc.perform(delete("/employees/{id}", 1).accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
    }

}
