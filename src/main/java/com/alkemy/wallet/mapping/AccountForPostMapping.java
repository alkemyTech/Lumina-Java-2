package com.alkemy.wallet.mapping;

import com.alkemy.wallet.dto.requestDto.AccountForPostRequestDTO;
import com.alkemy.wallet.dto.responseDto.AccountForPostResponseDTO;
import com.alkemy.wallet.model.AccountEntity;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AccountForPostMapping {

    public static AccountForPostResponseDTO convertEntityToDto(AccountEntity accountEntity) {
        return AccountForPostResponseDTO.builder()
                .accountId(accountEntity.getAccountId())
                .currency(accountEntity.getCurrency().toString())
                .transactionLimit(accountEntity.getTransactionLimit())
                .balance(accountEntity.getBalance())
                .user(UserEntityMapping.convertEntityToDTO(accountEntity.getUser()))
                .creationDate(accountEntity.getCreationDate())
                .updateDate(accountEntity.getUpdateDate())
                .build();
    }

    public static AccountEntity convertDtoToEntity(AccountForPostRequestDTO accountForPostRequestDTO) {
        return AccountEntity.builder()
                .transactionLimit(accountForPostRequestDTO.getTransactionLimit())
                .balance(accountForPostRequestDTO.getBalance())
                .user(UserEntityMapping.convertDtoToEntity(accountForPostRequestDTO.getUser()))
                .build();
    }

    public static List<AccountForPostResponseDTO> convertEntityListToDtoList(List<AccountEntity> accountEntityList) {
        return accountEntityList.stream().map(a -> convertEntityToDto(a)).collect(Collectors.toList());
    }

    public static List<AccountEntity> convertDTOListToEntityList(List<AccountForPostRequestDTO> accountDtoList) {
        return accountDtoList.stream().map(a -> convertDtoToEntity(a)).collect(Collectors.toList());
    }
}
