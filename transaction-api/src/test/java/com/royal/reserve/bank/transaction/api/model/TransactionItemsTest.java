package com.royal.reserve.bank.transaction.api.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the {@link TransactionItems} class.
 */
class TransactionItemsTest {

    private TransactionItems transactionItems;

    @BeforeEach
    void setUp() {
        transactionItems = new TransactionItems();
        transactionItems.setId(1L);
        transactionItems.setAssetCode("NDAQ");
        transactionItems.setAssetName("NASDAQ");
        transactionItems.setValue(11000);
    }

    /**
     * Test the constructors.
     */
    @Test
    void testGetId() {
        // When and Then
        assertEquals(1L, transactionItems.getId());
    }

    @Test
    void testGetAssetCode() {
        // When and Then
        assertEquals("NDAQ", transactionItems.getAssetCode());
    }

    @Test
    void testGetAssetName() {
        // When and Then
        assertEquals("NASDAQ", transactionItems.getAssetName());
    }

    @Test
    void testGetValue() {
        // When and Then
        assertEquals(11000, transactionItems.getValue());
    }

    @Test
    void testSetId() {
        // When
        transactionItems.setId(2L);

        // Then
        assertEquals(2L, transactionItems.getId());
    }

    @Test
    void testSetAssetCode() {
        // When
        transactionItems.setAssetCode("DXY");

        // Then
        assertEquals("DXY", transactionItems.getAssetCode());
    }

    @Test
    void testSetAssetName() {
        // When
        transactionItems.setAssetName("Walt Disney Company");

        // Then
        assertEquals("Walt Disney Company", transactionItems.getAssetName());
    }

    @Test
    void testSetValue() {
        // When
        transactionItems.setValue(200);

        // Then
        assertEquals(200, transactionItems.getValue());
    }
}

