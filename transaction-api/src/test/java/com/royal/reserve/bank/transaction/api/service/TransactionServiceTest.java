package com.royal.reserve.bank.transaction.api.service;

import com.royal.reserve.bank.transaction.api.client.AssetManagementClient;
import com.royal.reserve.bank.transaction.api.dto.AssetManagementResponse;
import com.royal.reserve.bank.transaction.api.dto.TransactionItemsDto;
import com.royal.reserve.bank.transaction.api.dto.TransactionRequest;
import com.royal.reserve.bank.transaction.api.event.TransactionEvent;
import com.royal.reserve.bank.transaction.api.model.Transaction;
import com.royal.reserve.bank.transaction.api.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link TransactionService} class.
 */
@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AssetManagementClient assetManagementClient;

    @Mock
    private KafkaTemplate<String, TransactionEvent> kafkaTemplate;

    /**
     * Test method for {@link TransactionService#processTransaction(TransactionRequest)}.
     */
    @Test
    void processTransactionWithAvailableAssets_shouldSaveTransactionAndSendNotification() {
        // Given
        transactionService = new TransactionService(transactionRepository,assetManagementClient,
                kafkaTemplate);
        TransactionRequest transactionRequest = new TransactionRequest();
        TransactionItemsDto transactionItemsDto = new TransactionItemsDto();
        transactionItemsDto.setAssetCode("DVN");
        transactionItemsDto.setAssetName("Devon Energy Corporation");
        transactionItemsDto.setValue(100);
        transactionRequest.setTransactionItemsDtoList(Collections.singletonList(transactionItemsDto));

        Transaction transaction = new Transaction();
        transaction.setTransactionId("transactionId");

        AssetManagementResponse assetManagementResponse = Mockito.mock(AssetManagementResponse.class);
        Mockito.when(assetManagementResponse.isAssetAvailable()).thenReturn(true);

        Mockito.when(assetManagementClient.checkAssetAvailability(ArgumentMatchers.anyList()))
                .thenReturn(Collections.singletonList(assetManagementResponse));
        Mockito.when(transactionRepository.save(ArgumentMatchers.any(Transaction.class))).thenReturn(transaction);

        // When
        String result = transactionService.processTransaction(transactionRequest);

        // Then
        assertEquals("Transaction completed successfully!", result);
        verify(transactionRepository, times(1)).save(any(Transaction.class));
        verify(kafkaTemplate, times(1)).send(eq("notificationTopic"),
                any(TransactionEvent.class));
    }

    /**
     * Test method for {@link TransactionService#processTransaction(TransactionRequest)}.
     */
    @Test
    void processTransactionWithUnavailableAssets_shouldThrowIllegalArgumentException() {
        // Given
        transactionService = new TransactionService(transactionRepository,assetManagementClient,
                kafkaTemplate);
        TransactionRequest transactionRequest = new TransactionRequest();
        TransactionItemsDto transactionItemsDto = new TransactionItemsDto();
        transactionItemsDto.setAssetCode("DM");
        transactionItemsDto.setAssetName("Desktop Metal, Inc.");
        transactionItemsDto.setValue(200);
        transactionRequest.setTransactionItemsDtoList(Collections.singletonList(transactionItemsDto));

        AssetManagementResponse assetManagementResponse = Mockito.mock(AssetManagementResponse.class);
        Mockito.when(assetManagementResponse.isAssetAvailable()).thenReturn(false);

        Mockito.when(assetManagementClient.checkAssetAvailability(ArgumentMatchers.anyList()))
                .thenReturn(Collections.singletonList(assetManagementResponse));

        // When and Then
        assertThrows(IllegalArgumentException.class,
                () -> transactionService.processTransaction(transactionRequest));
        verify(transactionRepository, never()).save(any(Transaction.class));
        verify(kafkaTemplate, never()).send(anyString(), any(TransactionEvent.class));
    }
}
