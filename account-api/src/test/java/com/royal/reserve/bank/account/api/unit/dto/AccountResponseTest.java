package com.royal.reserve.bank.account.api.unit.dto;

import com.royal.reserve.bank.account.api.dto.AccountResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Unit tests for the {@link AccountResponse} class.
 */
class AccountResponseTest {

    /**
     * Test the constructors.
     */
    @Test
    void testAccountResponse() {
        // Given
        String expectedId = "52342";
        String expectedAccountNumber = "BN12-4123-1235-7653-8576";
        String expectedAccountHolderName = "Nelson Mandela";
        BigDecimal expectedBalance = new BigDecimal("610345.00");
        Currency expectedCurrency = Currency.getInstance("EUR");

        // When
        AccountResponse response = AccountResponse.builder()
                .id(expectedId)
                .accountNumber(expectedAccountNumber)
                .accountHolderName(expectedAccountHolderName)
                .balance(expectedBalance)
                .currency(expectedCurrency)
                .build();

        // Then
        Assertions.assertEquals(expectedId, response.getId());
        Assertions.assertEquals(expectedAccountNumber, response.getAccountNumber());
        Assertions.assertEquals(expectedAccountHolderName, response.getAccountHolderName());
        Assertions.assertEquals(expectedBalance, response.getBalance());
        Assertions.assertEquals(expectedCurrency, response.getCurrency());
    }
}
