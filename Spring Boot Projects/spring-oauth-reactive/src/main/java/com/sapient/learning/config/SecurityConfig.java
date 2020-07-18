package com.sapient.learning.config;

import com.sapient.learning.utility.KeyUtility;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.security.interfaces.RSAPublicKey;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    @Profile("jwtDefault")
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        System.out.println("Instantiating Bean: springSecurityFilterChain");
        http
                .authorizeExchange(exchanges ->
                        exchanges.anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2ResourceServer ->
                        oauth2ResourceServer
                                .jwt(withDefaults())
                );
        return http.build();
    }

    @Bean
    @Profile("jwtWithJwkUri")
    SecurityWebFilterChain springSecurityFilterChainWithJwkUri(ServerHttpSecurity http) {
        System.out.println("Instantiating Bean: springSecurityFilterChainWithJwkUri");
        http
                .authorizeExchange()
                .anyExchange().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt()
                .jwkSetUri("http://localhost:8081/.well-known/jwks.json");
        return http.build();
    }

    @Bean
    @Profile("jwtWithPublicKey")
    SecurityWebFilterChain springSecurityFilterChainWithPublicKey(ServerHttpSecurity http) {
        System.out.println("Instantiating Bean: springSecurityFilterChainWithPublicKey");
        http
                .authorizeExchange()
                .anyExchange().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt()
                .publicKey((RSAPublicKey) KeyUtility.publicKey());
        return http.build();
    }

    @Bean
    @Profile("opaque")
    SecurityWebFilterChain springSecurityFilterChainWithIntrospectionUri(ServerHttpSecurity http) {
        System.out.println("Instantiating Bean: springSecurityFilterChainWithIntrospectionUri");
        http
                .authorizeExchange()
                .anyExchange().authenticated()
                .and()
                .oauth2ResourceServer()
                .opaqueToken().introspectionUri("http://localhost:8081/oauth/check_token")
                .introspectionClientCredentials("client","secret");
        return http.build();
    }

}
