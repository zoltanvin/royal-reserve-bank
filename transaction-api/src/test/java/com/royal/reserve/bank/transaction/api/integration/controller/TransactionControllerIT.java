package com.royal.reserve.bank.transaction.api.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.royal.reserve.bank.transaction.api.dto.TransactionItemsDto;
import com.royal.reserve.bank.transaction.api.dto.TransactionRequest;
import com.royal.reserve.bank.transaction.api.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class TransactionControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TransactionService transactionService;

    @Test
    void testProcessTransaction() throws Exception {
        TransactionRequest request = createTransactionRequest();
        when(transactionService.processTransaction(request))
                .thenReturn(String.valueOf(CompletableFuture.completedFuture("Result")));
        String jsonRequest = objectMapper.writeValueAsString(request);
        mockMvc.perform(post("/api/transaction")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void testProcessTransactionWithFallback() throws Exception {
        TransactionRequest request = createTransactionRequest();
        RuntimeException runtimeException = new RuntimeException("Simulated exception");
        when(transactionService.processTransaction(request)).thenThrow(runtimeException);
        String jsonRequest = objectMapper.writeValueAsString(request);
        mockMvc.perform(post("/api/transaction")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    private TransactionRequest createTransactionRequest() {
        List<TransactionItemsDto> transactionItemsDtoList = new ArrayList<>();

        TransactionItemsDto firstItem = new TransactionItemsDto();
        firstItem.setId(1L);
        firstItem.setAssetCode("MSFT");
        firstItem.setAssetName("Microsoft Corporation");
        firstItem.setValue(11300);
        transactionItemsDtoList.add(firstItem);

        TransactionItemsDto secondItem = new TransactionItemsDto();
        secondItem.setId(2L);
        secondItem.setAssetCode("COIN");
        secondItem.setAssetName("Coinbase Inc.");
        secondItem.setValue(24000);
        transactionItemsDtoList.add(secondItem);

        TransactionRequest request = new TransactionRequest();
        request.setTransactionItemsDtoList(transactionItemsDtoList);

        return request;
    }
}

