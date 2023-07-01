package com.royal.reserve.bank.account.api.util;

import com.royal.reserve.bank.account.api.model.Account;
import com.royal.reserve.bank.account.api.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

/**
 * The AccountTestData class is a component that implements the CommandLineRunner interface.
 * It is responsible for populating test data into the AccountRepository.
 */
@Component
@RequiredArgsConstructor
public class AccountTestData implements CommandLineRunner {
    private final AccountRepository accountRepository;
    @Override
    public void run(String... args) {
        List<Account> accountList = new ArrayList<>();
        Account accountItem1 = new Account();
        accountItem1.setId("8d7b1ef68a240c93f6e5d82c");
        accountItem1.setAccountNumber("CZ61-5051-2543-6888-5372");
        accountItem1.setAccountHolderName("Bruce Willis");
        accountItem1.setBalance(BigDecimal.valueOf(1340238));
        accountItem1.setCurrency(Currency.getInstance("EUR"));
        accountList.add(accountItem1);

        Account accountItem2 = new Account();
        accountItem2.setId("644d736d24862f0104ae52f7");
        accountItem2.setAccountNumber("GB46-4754-5577-4195-4725");
        accountItem2.setAccountHolderName("Harrison Ford");
        accountItem2.setBalance(BigDecimal.valueOf(25843));
        accountItem2.setCurrency(Currency.getInstance("GBP"));
        accountList.add(accountItem2);

        Account accountItem3 = new Account();
        accountItem3.setId("9f36a8e2b1d0c47b5e81c93d");
        accountItem3.setAccountNumber("FR59-1273-9000-5016-6948");
        accountItem3.setAccountHolderName("Scarlett Johansson");
        accountItem3.setBalance(BigDecimal.valueOf(14324));
        accountItem3.setCurrency(Currency.getInstance("EUR"));
        accountList.add(accountItem3);

        Account accountItem4 = new Account();
        accountItem4.setId("5e6f7c1d3a8b49h2k0j7m4n9");
        accountItem4.setAccountNumber("HU73-0472-9861-1757-7419");
        accountItem4.setAccountHolderName("Ryan Gosling");
        accountItem4.setBalance(BigDecimal.valueOf(8927382));
        accountItem4.setCurrency(Currency.getInstance("HUF"));
        accountList.add(accountItem4);

        accountRepository.saveAll(accountList);
    }
}

