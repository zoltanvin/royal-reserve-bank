package com.royal.reserve.bank.asset.management.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Unit tests for the {@link AssetManagementApiApplication} class.
 */
@SpringBootTest
class AssetManagementApiApplicationTests {
	@Test
	void contextLoads() {
		Assertions.assertDoesNotThrow(() -> {
			AssetManagementApiApplication.main(new String[]{});
		});
	}
}
