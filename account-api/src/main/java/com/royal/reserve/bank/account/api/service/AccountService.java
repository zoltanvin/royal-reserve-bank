package com.royal.reserve.bank.account.api.service;

import com.royal.reserve.bank.account.api.dto.AccountRequest;
import com.royal.reserve.bank.account.api.dto.AccountResponse;
import com.royal.reserve.bank.account.api.model.Account;
import com.royal.reserve.bank.account.api.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private static final Random random = new Random();
    private final AccountRepository accountRepository;

    public static String generateIBAN() {
        String[] countryCodes = Locale.getISOCountries();
        int index = random.nextInt(countryCodes.length);
        String countryCode = countryCodes[index];
        String accountNumber = String.format("%02d-%04d-%04d-%04d-%04d-%04d",
                random.nextInt(100),
                random.nextInt(10000),
                random.nextInt(10000),
                random.nextInt(10000),
                random.nextInt(10000),
                random.nextInt(10000));
        return countryCode + accountNumber;
    }

    public void createAccount(AccountRequest accountRequest) {
        Account account = Account.builder()
                .accountNumber(generateIBAN())
                .accountHolderName(accountRequest.getAccountHolderName())
                .balance(accountRequest.getBalance())
                .currency(accountRequest.getCurrency())
                .build();

        accountRepository.save(account);
        log.info("Account for {} is created", account.getAccountHolderName());
    }

    public List<AccountResponse> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();

        return accounts.stream().map(this::mapToAccountResponse).toList();
    }

    private AccountResponse mapToAccountResponse(Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .accountNumber(account.getAccountNumber())
                .accountHolderName(account.getAccountHolderName())
                .balance(account.getBalance())
                .currency(account.getCurrency())
                .build();
    }

    public void deleteAccountByAccountHolderName(String name) {
        List<Account> accounts = accountRepository.findAll();

        Optional<Account> accountToDelete = Optional.empty();
        for (Account a : accounts) {
            if (a.getAccountHolderName() != null && a.getAccountHolderName().equals(name)) {
                accountToDelete = Optional.of(a);
                break;
            }
        }

        if (accountToDelete.isPresent()) {
            accountRepository.delete(accountToDelete.get());
        } else {
            throw new NoSuchElementException("The bank account information for "
                    + name + " was not found.");
        }
    }
}
