package com.royal.reserve.bank.account.api.service;

import com.royal.reserve.bank.account.api.model.Account;
import com.royal.reserve.bank.account.api.repository.AccountRepository;
import com.royal.reserve.bank.account.api.dto.AccountResponse;
import com.royal.reserve.bank.account.api.dto.AccountRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service class that provides operations for managing bank accounts.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;

    private static final Random random = new Random();

    /**
     * Creates a new bank account.
     *
     * @param accountRequest The account request containing account details.
     */
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

    /**
     * Generates a random International Bank Account Number (IBAN).
     *
     * @return The generated IBAN.
     */
    public static String generateIBAN() {
        String[] countryCodes = Locale.getISOCountries();
        int index = random.nextInt(countryCodes.length);
        String countryCode = countryCodes[index];
        String accountNumber = String.format("%02d-%04d-%04d-%04d-%04d",
                random.nextInt(100),
                random.nextInt(10000),
                random.nextInt(10000),
                random.nextInt(10000),
                random.nextInt(10000));
        return countryCode + accountNumber;
    }

    /**
     * Retrieves all bank accounts.
     *
     * @return A list of AccountResponse objects representing the bank accounts.
     */
    public List<AccountResponse> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();

        return accounts.stream().map(this::mapToAccountResponse).toList();
    }

    /**
     * Maps an Account object to an AccountResponse object.
     *
     * @param account The Account object to map.
     * @return The mapped AccountResponse object.
     */
    private AccountResponse mapToAccountResponse(Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .accountNumber(account.getAccountNumber())
                .accountHolderName(account.getAccountHolderName())
                .balance(account.getBalance())
                .currency(account.getCurrency())
                .build();
    }

    /**
     * Deletes a bank account based on the account holder name.
     *
     * @param name The account holder name.
     * @throws NoSuchElementException if the account is not found.
     */
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
