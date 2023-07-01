package com.royal.reserve.bank.account.api.integration.config;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
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
}
