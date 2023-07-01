package com.royal.reserve.bank.account.api.unit.dto;


import com.royal.reserve.bank.account.api.dto.AccountRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Unit tests for the {@link AccountRequest} class.
 */
class AccountRequestTest {

    /**
     * Test the constructors.
     */
    @Test
    void testAccountRequest() {
        // Given
        String expectedAccountHolderName = "Steve Jobs";
        BigDecimal expectedBalance = new BigDecimal("13000.00");
        Currency expectedCurrency = Currency.getInstance("RUB");

        // When
        AccountRequest request = AccountRequest.builder()
                .accountHolderName(expectedAccountHolderName)
                .balance(expectedBalance)
                .currency(expectedCurrency)
                .build();

        // Then
        Assertions.assertEquals(expectedAccountHolderName, request.getAccountHolderName());
        Assertions.assertEquals(expectedBalance, request.getBalance());
        Assertions.assertEquals(expectedCurrency, request.getCurrency());
    }
}
