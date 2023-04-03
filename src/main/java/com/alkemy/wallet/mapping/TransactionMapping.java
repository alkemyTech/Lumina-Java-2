package com.alkemy.wallet.mapping;

import com.alkemy.wallet.dto.responseDto.TransactionResponseDTO;
import com.alkemy.wallet.model.Transaction;

public class TransactionMapping {

    public static TransactionResponseDTO convertEntityToDto(Transaction transaction){
        return TransactionResponseDTO.builder()
                .type(transaction.getType().name())
                .accountId(transaction.getAccount().getAccountId())
                .amount(transaction.getAmount())
                .description(transaction.getDescription())
                .transactionDate(transaction.getTransactionDate())
                .build();
    }
}
