package com.ethereal.learning;

import com.ethereal.learning.config.SecurityConfig;
import com.ethereal.learning.utility.TokenUtility;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest
@Import(SecurityConfig.class)
@ActiveProfiles("jwtWithPublicKey")
class JwtPublicKeyTests {

    @Autowired
    private WebTestClient webClient;

    @Test
    void getIndex() throws Exception {

    	webClient.get()
                .uri("/api/index")
                .headers(headers -> headers.setBearerAuth(TokenUtility.createToken("Kumar")))
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Hello, Kumar!");
    }

}
