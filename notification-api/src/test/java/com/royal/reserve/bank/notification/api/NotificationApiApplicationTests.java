package com.royal.reserve.bank.notification.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Unit tests for the {@link NotificationApiApplication} class.
 */
@SpringBootTest
class NotificationApiApplicationTests {
    @Test
    void contextLoads() {
        Assertions.assertDoesNotThrow(() -> {
            NotificationApiApplication.main(new String[]{});
        });
    }
}
