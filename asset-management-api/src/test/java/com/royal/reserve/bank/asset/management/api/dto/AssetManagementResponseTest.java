package com.royal.reserve.bank.asset.management.api.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link AssetManagementResponse} class.
 */
class AssetManagementResponseTest {

    /**
     * Test the constructors.
     */
    @Test
    void testAssetManagementResponse() {
        // Given
        String expectedAssetCode = "USDT";
        boolean expectedIsAssetAvailable = true;

        // When
        AssetManagementResponse response = AssetManagementResponse.builder()
                .assetCode(expectedAssetCode)
                .isAssetAvailable(expectedIsAssetAvailable)
                .build();

        // Then
        Assertions.assertEquals(expectedAssetCode, response.getAssetCode());
        Assertions.assertTrue(response.isAssetAvailable());
    }
}
