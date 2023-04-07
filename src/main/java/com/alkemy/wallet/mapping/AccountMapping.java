package com.alkemy.wallet.mapping;

import com.alkemy.wallet.dto.AccountDTO;
import com.alkemy.wallet.model.AccountEntity;

import java.util.ArrayList;
import java.util.List;

public abstract class AccountMapping {

    public static AccountDTO convertEntityToDto(AccountEntity account){
        return AccountDTO.builder()
                .accountId(account.getAccountId())
                .currency(account.getCurrency())
                .transactionLimit(account.getTransactionLimit())
                .balance(account.getBalance())
                .user(account.getUser())
                .creationDate(account.getCreationDate())
                .updateDate(account.getUpdateDate())
                .softDelete(account.getSoftDelete())
                .transactionList(null)
                .fixedTermDepositList(null)
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

    public static List<AccountDTO> convertEntityListToDtoList(List<AccountEntity> accountList){
        List<AccountDTO> ret = new ArrayList<>(accountList.size());
        for(AccountEntity account : accountList){
            ret.add(convertEntityToDto(account));
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
