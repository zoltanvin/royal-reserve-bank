package com.royal.reserve.bank.transaction.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionItemsDto {
    private Long id;
    private String assetCode;
    private String assetName;
    private int value;
}
