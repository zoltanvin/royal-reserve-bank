package com.royal.reserve.bank.transaction.api.unit.dto;

import com.royal.reserve.bank.transaction.api.dto.TransactionItemsDto;
import com.royal.reserve.bank.transaction.api.dto.TransactionRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Unit tests for the {@link TransactionRequest} class.
 */
class TransactionRequestTest {

    /**
     * Test the constructors.
     */
    @Test
    void testTransactionRequest() {
        // Given
        TransactionItemsDto item1 = new TransactionItemsDto(1L, "EDE", "Eden Innovations Ltd.", 100);
        TransactionItemsDto item2 = new TransactionItemsDto(2L, "T", "AT&T Inc.", 200);
        List<TransactionItemsDto> expectedItemList = Arrays.asList(item1, item2);

        // When
        TransactionRequest request = new TransactionRequest(expectedItemList);

        // Then
        Assertions.assertEquals(expectedItemList, request.getTransactionItemsDtoList());
    }
}
