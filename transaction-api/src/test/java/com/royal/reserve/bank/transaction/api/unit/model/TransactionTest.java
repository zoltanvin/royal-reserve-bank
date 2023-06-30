package com.royal.reserve.bank.transaction.api.unit.model;

import com.royal.reserve.bank.transaction.api.model.Transaction;
import com.royal.reserve.bank.transaction.api.model.TransactionItems;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the {@link Transaction} class.
 */
class TransactionTest {

    @Mock
    private TransactionItems mockTransactionItems1;

    @Mock
    private TransactionItems mockTransactionItems2;

    private Transaction transaction;

    @BeforeEach
    void setUp() {
        transaction = new Transaction();
        transaction.setId(1L);
        transaction.setTransactionId("9823427342");
        List<TransactionItems> transactionItemsList =
                Arrays.asList(mockTransactionItems1, mockTransactionItems2);
        transaction.setTransactionItemsList(transactionItemsList);
    }

    /**
     * Test the constructors.
     */
    @Test
    void testGetId() {
        // When and Then
        assertEquals(1L, transaction.getId());
    }

    @Test
    void testGetTransactionId() {
        // When and Then
        assertEquals("9823427342", transaction.getTransactionId());
    }

    @Test
    void testGetTransactionItemsList() {
        // Given
        List<TransactionItems> expectedItemsList = Arrays.asList(mockTransactionItems1, mockTransactionItems2);

        // When and Then
        assertEquals(expectedItemsList, transaction.getTransactionItemsList());
    }

    @Test
    void testSetId() {
        // When
        transaction.setId(2L);

        // Then
        assertEquals(2L, transaction.getId());
    }

    @Test
    void testSetTransactionId() {
        // When
        transaction.setTransactionId("987654321");

        // Then
        assertEquals("987654321", transaction.getTransactionId());
    }

    @Test
    void testSetTransactionItemsList() {
        // Given
        List<TransactionItems> newItemsList =
                Arrays.asList(mockTransactionItems1, mockTransactionItems2);

        // When
        transaction.setTransactionItemsList(newItemsList);

        // Then
        assertEquals(newItemsList, transaction.getTransactionItemsList());
    }
}
