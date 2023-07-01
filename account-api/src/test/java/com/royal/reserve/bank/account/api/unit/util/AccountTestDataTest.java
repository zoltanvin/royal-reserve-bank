package com.royal.reserve.bank.account.api.unit.util;

import com.royal.reserve.bank.account.api.model.Account;
import com.royal.reserve.bank.account.api.repository.AccountRepository;
import com.royal.reserve.bank.account.api.util.AccountTestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link AccountTestData} class.
 */
@ExtendWith(MockitoExtension.class)
class AccountTestDataTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountTestData accountTestData;

    /**
     * Test for the {@link AccountTestData#run(String...)} method.
     */
    @Test
    void testRun() {
        // Given
        List<Account> accountList = createAccountList();

        // When
        accountTestData.run();

        // Then
        verify(accountRepository, times(1)).saveAll(accountList);
    }

    /**
     * Creates a list of {@link Account} objects for testing purposes.
     *
     * @return the list of account objects
     */
    private List<Account> createAccountList() {
        List<Account> accountList = new ArrayList<>();

        Account accountItem1 = createAccount("8d7b1ef68a240c93f6e5d82c", "CZ61-5051-2543-6888-5372",
                "Bruce Willis", BigDecimal.valueOf(1340238), Currency.getInstance("EUR"));
        accountList.add(accountItem1);

        Account accountItem2 = createAccount("644d736d24862f0104ae52f7", "GB46-4754-5577-4195-4725",
                "Harrison Ford", BigDecimal.valueOf(25843), Currency.getInstance("GBP"));
        accountList.add(accountItem2);

        Account accountItem3 = createAccount("9f36a8e2b1d0c47b5e81c93d", "FR59-1273-9000-5016-6948",
                "Scarlett Johansson", BigDecimal.valueOf(14324), Currency.getInstance("EUR"));
        accountList.add(accountItem3);

        Account accountItem4 = createAccount("5e6f7c1d3a8b49h2k0j7m4n9", "HU73-0472-9861-1757-7419",
                "Ryan Gosling", BigDecimal.valueOf(8927382), Currency.getInstance("HUF"));
        accountList.add(accountItem4);

        return accountList;
    }

    /**
     * Creates an {@link Account} object with the provided details.
     *
     * @param id                the account ID
     * @param accountNumber     the account number
     * @param accountHolderName the account holder's name
     * @param balance           the account balance
     * @param currency          the account currency
     * @return the created account object
     */
    private Account createAccount(String id, String accountNumber, String accountHolderName,
                                  BigDecimal balance, Currency currency) {
        Account account = new Account();
        account.setId(id);
        account.setAccountNumber(accountNumber);
        account.setAccountHolderName(accountHolderName);
        account.setBalance(balance);
        account.setCurrency(currency);
        return account;
    }
}
