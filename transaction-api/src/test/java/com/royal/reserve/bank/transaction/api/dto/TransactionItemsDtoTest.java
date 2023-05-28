package com.royal.reserve.bank.transaction.api.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link TransactionItemsDto} class.
 */
class TransactionItemsDtoTest {

    /**
     * Test the constructors.
     */
    @Test
    void testTransactionItemsDto() {
        // Given
        Long expectedId = 1L;
        String expectedAssetCode = "IBM";
        String expectedAssetName = "International Business Machines Corporation";
        int expectedValue = 942200;

        // When
        TransactionItemsDto dto = new TransactionItemsDto(expectedId, expectedAssetCode, expectedAssetName, expectedValue);

        // Then
        Assertions.assertEquals(expectedId, dto.getId());
        Assertions.assertEquals(expectedAssetCode, dto.getAssetCode());
        Assertions.assertEquals(expectedAssetName, dto.getAssetName());
        Assertions.assertEquals(expectedValue, dto.getValue());
    }
}

