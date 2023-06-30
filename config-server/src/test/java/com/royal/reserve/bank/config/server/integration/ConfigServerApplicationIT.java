package com.royal.reserve.bank.config.server.integration;

import com.royal.reserve.bank.config.server.ConfigServerApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Integration tests for the {@link ConfigServerApplication} class.
 */
@SpringBootTest
class ConfigServerApplicationIT {
    @Test
    void contextLoads() {
        Assertions.assertDoesNotThrow(() -> {
            ConfigServerApplication.main(new String[]{});
        });
    }
}
