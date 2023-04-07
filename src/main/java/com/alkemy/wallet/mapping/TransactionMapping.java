package com.alkemy.wallet.mapping;

import com.alkemy.wallet.dto.responseDto.TransactionResponseDTO;
import com.alkemy.wallet.model.TransactionEntity;

import java.util.ArrayList;
import java.util.List;

public class TransactionMapping {

    public static TransactionResponseDTO convertEntityToDto(TransactionEntity transactionEntity){
        return TransactionResponseDTO.builder()
                .type(transactionEntity.getType().name())
                .accountId(transactionEntity.getAccountEntity().getAccountId())
                .amount(transactionEntity.getAmount())
                .description(transactionEntity.getDescription())
                .transactionDate(transactionEntity.getTransactionDate())
                .build();
    }

    public static List<TransactionResponseDTO> convertEntityListToDtoList(List<TransactionEntity> transactionEntityList){
        List<TransactionResponseDTO> ret = new ArrayList<>(transactionEntityList.size());
        for(TransactionEntity transactionEntity : transactionEntityList){
            ret.add(convertEntityToDto(transactionEntity));
        }
        return ret;
    }
}
