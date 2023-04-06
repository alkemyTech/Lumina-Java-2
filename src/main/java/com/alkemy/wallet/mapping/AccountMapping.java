package com.alkemy.wallet.mapping;

import com.alkemy.wallet.dto.AccountDTO;
import com.alkemy.wallet.model.AccountEntity;

import java.util.ArrayList;
import java.util.List;

public abstract class AccountMapping {

    public static AccountDTO convertEntityToDto(AccountEntity accountEntity){
        return AccountDTO.builder()
                .accountId(accountEntity.getAccountId())
                .currency(accountEntity.getCurrency())
                .transactionLimit(accountEntity.getTransactionLimit())
                .balance(accountEntity.getBalance())
                .user(accountEntity.getUser())
                .creationDate(accountEntity.getCreationDate())
                .updateDate(accountEntity.getUpdateDate())
                .softDelete(accountEntity.getSoftDelete())
                .transactionEntityList(null)
                .fixedTermDepositEntityList(null)
                .build();
    }

    public static AccountEntity convertDtoToEntity(AccountDTO accountDTO){
        return AccountEntity.builder()
                .currency(accountDTO.getCurrency())
                .transactionLimit(accountDTO.getTransactionLimit())
                .balance(accountDTO.getBalance())
                .user(accountDTO.getUser())
                .build();
    }

    public static List<AccountDTO> convertEntityListToDtoList(List<AccountEntity> accountEntityList){
        List<AccountDTO> ret = new ArrayList<>(accountEntityList.size());
        for(AccountEntity accountEntity : accountEntityList){
            ret.add(convertEntityToDto(accountEntity));
        }
        return ret;
    }

    public static List<AccountEntity> convertDTOListToEntityList(List<AccountDTO> accountDtoList){
        List<AccountEntity> ret = new ArrayList<>(accountDtoList.size());
        for(AccountDTO accountDto : accountDtoList){
            ret.add(convertDtoToEntity(accountDto));
        }
        return ret;
    }
}
