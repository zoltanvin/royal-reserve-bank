package com.royal.reserve.bank.account.api.unit.service;

import com.royal.reserve.bank.account.api.dto.AccountRequest;
import com.royal.reserve.bank.account.api.dto.AccountResponse;
import com.royal.reserve.bank.account.api.model.Account;
import com.royal.reserve.bank.account.api.repository.AccountRepository;
import com.royal.reserve.bank.account.api.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link AccountService} class.
 */
@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Mock
    private RedisTemplate<String, List<AccountResponse>> redisTemplate;

    /**
     * Test for the {@link AccountService#createAccount(AccountRequest)} method.
     */
    @Test
    void testCreateAccount() {
        // Given
        AccountRequest accountRequest = createAccountRequest("Matthew McConaughey",
                BigDecimal.valueOf(1000), Currency.getInstance("USD"));
        Account account = createAccount("FI32-8473-8127-1823-1732", "Matthew McConaughey",
                BigDecimal.valueOf(1000), Currency.getInstance("USD"));

        when(accountRepository.save(any(Account.class))).thenReturn(account);

        // When
        accountService.createAccount(accountRequest);

        // Then
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    /**
     * Test for the {@link AccountService#generateIBAN()} method.
     */
    @Test
    void testGenerateIBAN() {
        // When
        String iban = AccountService.generateIBAN();

        // Then
        assertNotNull(iban);
        assertTrue(iban.matches("[A-Z]{2}\\d{2}-\\d{4}-\\d{4}-\\d{4}-\\d{4}"));
    }

    /**
     * Test for the {@link AccountService#getAllAccounts()} method.
     */
    @Test
    @SuppressWarnings("unchecked")
    void testGetAllAccounts() {
        // Given
        Account account1 = createAccount("MT23-3821-4829-3279-9231",
                "Barack Obama", BigDecimal.valueOf(1000), Currency.getInstance("USD"));
        Account account2 = createAccount("NE23-3948-4762-3721-8392",
                "Michelle Obama", BigDecimal.valueOf(2000), Currency.getInstance("EUR"));
        List<Account> accounts = new ArrayList<>();
        accounts.add(account1);
        accounts.add(account2);

        when(accountRepository.findAll()).thenReturn(accounts);
        when(redisTemplate.opsForValue()).thenReturn(mock(ValueOperations.class));

        // When
        List<AccountResponse> accountResponses = accountService.getAllAccounts();

        // Then
        verify(accountRepository, times(1)).findAll();
    }

    /**
     * Test for the {@link AccountService#deleteAccountByAccountHolderName(String)} method.
     */
    @Test
    void testDeleteAccountByAccountHolderName() {
        // Given
        String accountHolderName = "Barack Obama";
        Account account = createAccount("MT23-3821-4829-3279-9231", accountHolderName,
                BigDecimal.valueOf(1000), Currency.getInstance("USD"));
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);

        when(accountRepository.findAll()).thenReturn(accounts);
        doNothing().when(accountRepository).delete(account);

        // When
        assertDoesNotThrow(() -> accountService.deleteAccountByAccountHolderName(accountHolderName));

        // Then
        verify(accountRepository, times(1)).findAll();
        verify(accountRepository, times(1)).delete(account);
    }

    /**
     * Test for the {@link AccountService#deleteAccountByAccountHolderName(String)} method when account is not found.
     */
    @Test
    void testDeleteAccountByAccountHolderNameAccountNotFound() {
        // Given
        String accountHolderName = "Johnny Depp";
        List<Account> accounts = new ArrayList<>();

        when(accountRepository.findAll()).thenReturn(accounts);

        // When
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () ->
                accountService.deleteAccountByAccountHolderName(accountHolderName));

        // Then
        assertEquals("The bank account information for " + accountHolderName + " was not found.",
                exception.getMessage());
        verify(accountRepository, times(1)).findAll();
    }

    // Helper methods

    /**
     * Helper method to create an {@link AccountRequest} object with the given data.
     *
     * @param accountHolderName the account holder name
     * @param balance           the account balance
     * @param currency          the account currency
     * @return the created {@link AccountRequest} object
     */
    private AccountRequest createAccountRequest(String accountHolderName, BigDecimal balance, Currency currency) {
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setAccountHolderName(accountHolderName);
        accountRequest.setBalance(balance);
        accountRequest.setCurrency(currency);
        return accountRequest;
    }

    /**
     * Helper method to create an {@link Account} object with the given data.
     *
     * @param accountNumber     the account number
     * @param accountHolderName the account holder name
     * @param balance           the account balance
     * @param currency          the account currency
     * @return the created {@link Account} object
     */
    private Account createAccount(String accountNumber, String accountHolderName,
                                  BigDecimal balance, Currency currency) {
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setAccountHolderName(accountHolderName);
        account.setBalance(balance);
        account.setCurrency(currency);
        return account;
    }
}
