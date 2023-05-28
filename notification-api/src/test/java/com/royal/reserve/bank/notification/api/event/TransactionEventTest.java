package com.royal.reserve.bank.notification.api.event;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link TransactionEvent} class.
 */
class TransactionEventTest {

    /**
     * Test for the {@link TransactionEvent#TransactionEvent(String)} constructor.
     */
    @Test
    void testTransactionEvent() {
        // Given
        String expectedTransactionId = "13645940";

        // When
        TransactionEvent event = new TransactionEvent(expectedTransactionId);

        // Then
        Assertions.assertEquals(expectedTransactionId, event.getTransactionId());
    }
}

