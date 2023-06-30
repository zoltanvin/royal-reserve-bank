package com.royal.reserve.bank.account.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Represents a bank account.
 */
@Document(value = "account")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Account {
    @Id
    private String id;
    private String accountNumber;
    private String accountHolderName;
    private BigDecimal balance;
    private Currency currency;
}
