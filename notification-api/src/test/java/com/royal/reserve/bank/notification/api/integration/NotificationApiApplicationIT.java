package com.royal.reserve.bank.notification.api.integration;

import com.royal.reserve.bank.notification.api.NotificationApiApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Integration tests for the {@link NotificationApiApplication} class.
 */
@SpringBootTest
class NotificationApiApplicationIT {
    @Test
    void contextLoads() {
        Assertions.assertDoesNotThrow(() -> {
            NotificationApiApplication.main(new String[]{});
        });
    }
}
