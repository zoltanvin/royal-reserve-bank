package com.royal.reserve.bank.config.server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Unit tests for the {@link ConfigServerApplication} class.
 */
@SpringBootTest
class ConfigServerApplicationTests {
    @Test
    void contextLoads() {
        Assertions.assertDoesNotThrow(() -> {
            ConfigServerApplication.main(new String[]{});
        });
    }
}
