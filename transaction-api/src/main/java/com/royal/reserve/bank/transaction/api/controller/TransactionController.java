package com.royal.reserve.bank.transaction.api.controller;

import com.royal.reserve.bank.transaction.api.dto.TransactionRequest;
import com.royal.reserve.bank.transaction.api.service.TransactionService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "asset-management", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "asset-management")
    @Retry(name = "asset-management")
    public CompletableFuture<String> processTransaction(@RequestBody TransactionRequest transactionRequest) {
        log.info("Transaction processed.");
        return CompletableFuture.supplyAsync(() -> transactionService.processTransaction(transactionRequest));
    }

    public CompletableFuture<String> fallbackMethod(TransactionRequest transactionRequest, RuntimeException runtimeException) {
        log.info("Transaction can't be processed. Executing fallback logic.");
        return CompletableFuture.supplyAsync(() -> "Oops! Something went wrong, please try again later!");
    }
}