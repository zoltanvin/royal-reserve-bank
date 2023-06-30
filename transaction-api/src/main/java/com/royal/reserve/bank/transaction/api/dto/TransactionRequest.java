package com.royal.reserve.bank.transaction.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data Transfer Object (DTO) class that represents the request payload for a transaction.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
    private List<TransactionItemsDto> transactionItemsDtoList;
}
