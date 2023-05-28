package com.royal.reserve.bank.api.gateway;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Unit tests for the {@link ApiGatewayApplication} class.
 */
@SpringBootTest
class ApiGatewayApplicationTests {
    @Test
    void contextLoads() {
        Assertions.assertDoesNotThrow(() -> {
            ApiGatewayApplication.main(new String[]{});
        });
    }
}
