package com.alkemy.wallet.mapping;

import com.alkemy.wallet.dto.responseDto.TransactionResponseDTO;
import com.alkemy.wallet.model.Transaction;

import java.util.ArrayList;
import java.util.List;

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

    public static List<TransactionResponseDTO> convertEntityListToDtoList(List<Transaction> transactionList){
        List<TransactionResponseDTO> ret = new ArrayList<>(transactionList.size());
        for(Transaction transaction : transactionList){
            ret.add(convertEntityToDto(transaction));
        }
        return ret;
    }
}
