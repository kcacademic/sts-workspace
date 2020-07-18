package com.sapient.learning;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.sapient.learning.config.SecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@WebFluxTest
@Import(SecurityConfig.class)
@ActiveProfiles("opaque")
class OpaqueIntrospectionUriTests {

    @Autowired
    private WebTestClient webClient;

    static WireMockServer wireMockServer;

    private String token = "dummy";
    private String body = "{\"active\": \"true\",\"client_id\": \"Kumar\"}";

    @BeforeEach
    public void setup() {
        wireMockServer = new WireMockServer(8081);
        configureFor("localhost", 8081);
        wireMockServer.start();
        stubFor(post(urlPathMatching("/oauth/check_token"))
                .willReturn(aResponse().withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));
    }

    @Test
    void getIndex() throws Exception {

    	webClient.get()
                .uri("/api/index")
                .headers(headers -> headers.setBearerAuth(token))
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Hello, subject!");
    }

}
