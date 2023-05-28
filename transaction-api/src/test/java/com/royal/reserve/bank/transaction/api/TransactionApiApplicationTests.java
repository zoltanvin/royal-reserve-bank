package com.royal.reserve.bank.transaction.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Unit tests for the {@link TransactionApiApplication} class.
 */
@SpringBootTest
class TransactionApiApplicationTests {
	@Test
	void contextLoads() {
		Assertions.assertDoesNotThrow(() -> {
			TransactionApiApplication.main(new String[]{});
		});
	}
}
