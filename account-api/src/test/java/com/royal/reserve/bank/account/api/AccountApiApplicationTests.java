package com.royal.reserve.bank.account.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Unit tests for the {@link AccountApiApplication} class.
 */
@SpringBootTest
class AccountApiApplicationTests {
    @Test
    void contextLoads() {
        Assertions.assertDoesNotThrow(() -> {
            AccountApiApplication.main(new String[]{});
        });
    }
}
