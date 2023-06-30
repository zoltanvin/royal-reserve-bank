package com.royal.reserve.bank.transaction.api.unit.event;

import com.royal.reserve.bank.transaction.api.event.TransactionEvent;
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
        String expectedTransactionId = "876463547";

        // When
        TransactionEvent event = new TransactionEvent(expectedTransactionId);

        // Then
        Assertions.assertEquals(expectedTransactionId, event.getTransactionId());
    }
}
