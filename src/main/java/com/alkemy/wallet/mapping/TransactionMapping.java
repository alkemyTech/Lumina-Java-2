package com.alkemy.wallet.mapping;

import com.alkemy.wallet.Exception.InvalidResourceException;
import com.alkemy.wallet.dto.requestDto.TransactionRequestDTO;
import com.alkemy.wallet.dto.responseDto.TransactionResponseDTO;
import com.alkemy.wallet.dto.responseDto.Transactions4AccountDTO;
import com.alkemy.wallet.model.TransactionEntity;
import com.alkemy.wallet.model.Type;

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
    public static Type getTypeTransaction(String type){
        Type typeTransaction = null;
        if(type.equals(Type.INCOME.name())){
            typeTransaction = Type.INCOME;
        } else if(type.equals(Type.DEPOSIT.name())){
            typeTransaction = Type.DEPOSIT;
        }else if(type.equals(Type.PAYMENT.name())){
            typeTransaction = Type.PAYMENT;
        }else{
            throw new InvalidResourceException("El tipo de transaccion no existe");
        }
        return typeTransaction ;
    }


    public static TransactionEntity convertDtoToEntity(TransactionRequestDTO transactionRequestDTO) {
        return TransactionEntity.builder()
                .amount(transactionRequestDTO.getAmount())
                .type(getTypeTransaction(transactionRequestDTO.getType()))
                .description(transactionRequestDTO.getDescription())
                .build();
    }

    public static Transactions4AccountDTO convertTransaction4AccountToDto(TransactionEntity transactionEntity){
        return Transactions4AccountDTO.builder()
                .type(transactionEntity.getType().name())
                .amount(transactionEntity.getAmount())
                .description(transactionEntity.getDescription())
                .transactionDate(transactionEntity.getTransactionDate())
                .build();
    }

    public static List<Transactions4AccountDTO> convertTransactions4AccountListToDtoList(List<TransactionEntity> transactionEntityList){
        List<Transactions4AccountDTO> transactions4AccountDTOSList = new ArrayList<>();
        for(TransactionEntity transactionEntity : transactionEntityList){
            transactions4AccountDTOSList.add(convertTransaction4AccountToDto(transactionEntity));
        }
        return transactions4AccountDTOSList;
    }
}
