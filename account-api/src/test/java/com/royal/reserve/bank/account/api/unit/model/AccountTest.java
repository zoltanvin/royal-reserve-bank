package com.royal.reserve.bank.account.api.unit.model;

import com.royal.reserve.bank.account.api.model.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Currency;

import static org.mockito.Mockito.when;

/**
 * Unit tests for the {@link Account} class.
 */
@ExtendWith(MockitoExtension.class)
class AccountTest {

    @Mock
    private Account account;

    /**
     * Test the constructors.
     */
    @Test
    void testAccount() {
        // Given
        String expectedId = "2742431";
        String expectedAccountNumber = "GR46-4391-5577-4195-4725";
        String expectedAccountHolderName = "Matt Damon";
        BigDecimal expectedBalance = new BigDecimal("211000.00");
        Currency expectedCurrency = Currency.getInstance("USD");

        // When
        when(account.getId()).thenReturn(expectedId);
        when(account.getAccountNumber()).thenReturn(expectedAccountNumber);
        when(account.getAccountHolderName()).thenReturn(expectedAccountHolderName);
        when(account.getBalance()).thenReturn(expectedBalance);
        when(account.getCurrency()).thenReturn(expectedCurrency);

        // Then
        Assertions.assertEquals(expectedId, account.getId());
        Assertions.assertEquals(expectedAccountNumber, account.getAccountNumber());
        Assertions.assertEquals(expectedAccountHolderName, account.getAccountHolderName());
        Assertions.assertEquals(expectedBalance, account.getBalance());
        Assertions.assertEquals(expectedCurrency, account.getCurrency());
    }
}
