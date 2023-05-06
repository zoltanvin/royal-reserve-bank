package com.mini.shopify.api.gateway.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.server.WebFilter;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.stream.Collectors;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;

@Configuration
@EnableWebFluxSecurity
@Getter
public class SecurityConfig {
    @Value("${encodedJwt}")
    private String encodedJwt;

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String jwkSetUri;

    @Value("${pemUrl}")
    private String pemUrl;

    private String jwtToken;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) throws IOException, JwkException, NoSuchAlgorithmException, InvalidKeySpecException {
        //https://stackoverflow.com/questions/66389070/generating-public-key-from-jwk
        //https://github.com/auth0/jwks-rsa-java/blob/master/src/main/java/com/auth0/jwk/Jwk.java#L176
        final DecodedJWT jwt = JWT.decode(encodedJwt);
        //final DecodedJWT jwt = JWT.decode(getJwtToken());
        RSAPublicKey publicKey = loadPublicKey(jwt);
        //RSAPublicKey publicKey = readPublicKeyFromPemUrl(pemUrl);

        serverHttpSecurity
                .csrf().disable()
                .addFilterAt(jwtFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
                .oauth2ResourceServer()
                .jwt()
                .publicKey(publicKey)
                .and()
                .and()
                .authorizeExchange(exchange ->
                        exchange.pathMatchers("/eureka/**", "/discovery-server/**")
                                .permitAll()
                                .anyExchange()
                                .authenticated());
        return serverHttpSecurity.build();
    }

    private RSAPublicKey loadPublicKey(DecodedJWT token) throws JwkException, MalformedURLException {
        JwkProvider provider = new UrlJwkProvider(new URL(jwkSetUri));
        return (RSAPublicKey) provider.get(token.getKeyId()).getPublicKey();
    }

    public RSAPublicKey readPublicKeyFromPemUrl(String pemUrl) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        URL url = new URL(pemUrl);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String pem = reader.lines().collect(Collectors.joining());
        pem = pem
                .replace("-----BEGIN CERTIFICATE-----", "")
                .replace("-----END CERTIFICATE-----", "")
                .replaceAll(System.lineSeparator(), "");

        byte[] encoded = Base64.getDecoder().decode(pem);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
        return (RSAPublicKey) keyFactory.generatePublic(keySpec);
    }

    @Bean
    WebFilter jwtFilter() {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String authorizationHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                jwtToken = authorizationHeader.substring(7);
            }
            //System.out.println("WebFilter gets called. Token: " + jwtToken);
            return chain.filter(exchange);
        };
    }
}
