package com.alkemy.wallet.mapping;

import com.alkemy.wallet.dto.AccountDTO;
import com.alkemy.wallet.model.Account;

import java.util.ArrayList;
import java.util.List;

public abstract class AccountMapping {

    public static AccountDTO convertEntityToDto(Account account){
        return AccountDTO.builder()
                .accountId(account.getAccountId())
                .currency(account.getCurrency())
                .transactionLimit(account.getTransactionLimit())
                .balance(account.getBalance())
                .user(account.getUser())
                .creationDate(account.getCreationDate())
                .updateDate(account.getUpdateDate())
                .softDelete(account.getSoftDelete())
                .transactionList(account.getTransactionList())
                .fixedTermDepositList(account.getFixedTermDepositList())
                .build();
    }

    public static Account convertDtoToEntity(AccountDTO accountDTO){
        return Account.builder()
                .currency(accountDTO.getCurrency())
                .transactionLimit(accountDTO.getTransactionLimit())
                .balance(accountDTO.getBalance())
                .user(accountDTO.getUser())
                .build();
    }

    public static List<AccountDTO> convertEntityListToDtoList(List<Account> accountList){
        List<AccountDTO> ret = new ArrayList<>(accountList.size());
        for(Account account : accountList){
            ret.add(convertEntityToDto(account));
        }
        return ret;
    }

    public static List<Account> convertDTOListToEntityList(List<AccountDTO> accountDtoList){
        List<Account> ret = new ArrayList<>(accountDtoList.size());
        for(AccountDTO accountDto : accountDtoList){
            ret.add(convertDtoToEntity(accountDto));
        }
        return ret;
    }
}
