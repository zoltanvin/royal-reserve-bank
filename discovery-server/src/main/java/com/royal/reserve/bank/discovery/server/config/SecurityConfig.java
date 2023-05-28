package com.royal.reserve.bank.discovery.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration class for web security.
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    /**
     *Configures the security filter chain for the application. Ignore the /eureka/** path from CSRF protection.
     *@param httpSecurity the HttpSecurity object used for configuring the security filter chain
     *@return the configured SecurityFilterChain object
     *@throws Exception if an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().ignoringRequestMatchers("/eureka/**");
        return httpSecurity.build();
    }
}
