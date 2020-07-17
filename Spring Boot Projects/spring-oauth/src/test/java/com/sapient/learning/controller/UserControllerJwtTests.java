package com.sapient.learning.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("jwt")
public class UserControllerJwtTests {

	@Autowired
	private MockMvc mockMvc;
	
	@SuppressWarnings("unused")
	private String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsib2F1dGgyLXJlc291cmNlIl0sInVzZXJfbmFtZSI6InVzZXIiLCJzY29wZSI6WyJyZWFkX3Byb2ZpbGVfaW5mbyJdLCJleHAiOjE1OTQ5ODc3OTgsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJqdGkiOiJmNjViNzk0My02NGE0LTRiZmMtODg2YS00NzRjZDE4NmQ2ZDgiLCJjbGllbnRfaWQiOiJjbGllbnRhcHAifQ.WCkBGWixsvs4au3SFZr528o0BovyOQYfxuFjouQPoMw";

	@Test
	public void getUser() throws Exception {
		mockMvc.perform(get("/api/users/me").header(HttpHeaders.AUTHORIZATION, "Bearer " + createToken("user"))).andDo(print())
				.andExpect(status().isOk()).andExpect(content().string(
						containsString("{\"name\":\"user\",\"email\":\"user@sapient.com\",\"authorities\":\"[ROLE_USER]\"}")));
	}

	public static String createToken(String username) throws ParseException {
		Claims claims = Jwts.claims().setSubject(username);
		claims.put("user_name", username);
		claims.put("authorities", "ROLE_USER");
		String jwt = Jwts.builder()
				.setClaims(claims)
				.setExpiration(new SimpleDateFormat("dd/MM/yyyy").parse("23/10/2030"))
				.signWith(SignatureAlgorithm.HS256, "123".getBytes()).compact();
		return jwt;
	}

}