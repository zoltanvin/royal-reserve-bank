package com.royal.reserve.bank.discovery.server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Unit tests for the {@link DiscoveryServerApplication} class.
 */
@SpringBootTest
class DiscoveryServerApplicationTests {
    @Test
    void contextLoads() {
        Assertions.assertDoesNotThrow(() -> {
            DiscoveryServerApplication.main(new String[]{});
        });
    }
}
