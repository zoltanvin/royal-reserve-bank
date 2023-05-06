package com.royal.reserve.bank.account.api.controller;

import com.royal.reserve.bank.account.api.service.AccountService;
import com.royal.reserve.bank.account.api.dto.AccountResponse;
import com.royal.reserve.bank.account.api.dto.AccountRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<String> createAccount(@RequestBody AccountRequest accountRequest) {
        accountService.createAccount(accountRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body
                ("Successfully set up a new bank account for " +
                accountRequest.getAccountHolderName() + ".");
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AccountResponse> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAccount(@RequestBody AccountRequest accountRequest) {
        try {
            accountService.deleteAccountByAccountHolderName(accountRequest.getAccountHolderName());
            return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted " +
                    accountRequest.getAccountHolderName() + "'s account.");
        } catch (RuntimeException runtimeException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(runtimeException.getMessage());
        }
    }
}
