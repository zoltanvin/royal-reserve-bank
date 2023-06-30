package com.royal.reserve.bank.transaction.api.unit.controller;

import com.royal.reserve.bank.transaction.api.controller.TransactionController;
import com.royal.reserve.bank.transaction.api.dto.TransactionItemsDto;
import com.royal.reserve.bank.transaction.api.dto.TransactionRequest;
import com.royal.reserve.bank.transaction.api.service.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the {@link TransactionController} class.
 */
@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    private TransactionRequest request;

    @BeforeEach
    void setup() {
        TransactionItemsDto item1 = new TransactionItemsDto(1L, "AAPL", "Apple Inc.",
                19000);
        TransactionItemsDto item2 = new TransactionItemsDto(2L, "GOOGL", "Alphabet Inc.",
                10200);
        List<TransactionItemsDto> transactionItemList = Arrays.asList(item1, item2);
        request = new TransactionRequest(transactionItemList);
    }

    /**
     * Test for the {@link TransactionController#processTransaction(TransactionRequest)} method.
     */
    @Test
    void testProcessTransaction() {
        // Given
        String expectedResponse = "Transaction processed.";
        when(transactionService.processTransaction(any(TransactionRequest.class))).thenReturn(expectedResponse);

        // When
        CompletableFuture<String> result = transactionController.processTransaction(request);

        // Then
        Assertions.assertEquals(expectedResponse, result.join());
    }

    /**
     * Test for the {@link TransactionController#fallbackMethod(TransactionRequest, RuntimeException)} method.
     */
    @Test
    void testFallbackMethod() {
        // Given
        RuntimeException exception = new RuntimeException("Error occurred during transaction processing");
        String expectedFallbackResponse = "Oops! Something went wrong, please try again later!";

        // When
        CompletableFuture<String> result = transactionController.fallbackMethod(request, exception);

        // Then
        Assertions.assertEquals(expectedFallbackResponse, result.join());
    }
}
