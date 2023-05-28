package com.royal.reserve.bank.account.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Data Transfer Object (DTO) class that represents the response payload for a bank account holder.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
    private String id;
    private String accountNumber;
    private String accountHolderName;
    private BigDecimal balance;
    private Currency currency;
}
