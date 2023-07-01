package com.royal.reserve.bank.discovery.server.integration;

import com.royal.reserve.bank.discovery.server.DiscoveryServerApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Integration tests for the {@link DiscoveryServerApplication} class.
 */
@SpringBootTest
class DiscoveryServerApplicationIT {
    @Test
    void contextLoads() {
        Assertions.assertDoesNotThrow(() -> {
            DiscoveryServerApplication.main(new String[]{});
        });
    }
}
