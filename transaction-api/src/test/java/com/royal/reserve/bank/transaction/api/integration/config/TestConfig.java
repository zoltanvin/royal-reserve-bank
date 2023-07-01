package com.royal.reserve.bank.transaction.api.integration.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Test configuration for the integration tests.
 */
@TestConfiguration
@AutoConfigureMockMvc
public class TestConfig {
    /**
     * Creates a {@link MockMvc} instance.
     *
     * @param webApplicationContext the web application context
     * @return the {@link MockMvc} instance
     */
    @Bean
    public MockMvc mockMvc(WebApplicationContext webApplicationContext) {
        return MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * Creates an {@link ObjectMapper} bean.
     *
     * @return the {@link ObjectMapper} bean
     */
    @Bean
    public ObjectMapper objectMapper() {
        return Jackson2ObjectMapperBuilder.json().build();
    }
}
