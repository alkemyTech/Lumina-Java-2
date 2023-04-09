package com.alkemy.wallet.service.service;

import com.alkemy.wallet.dto.AccountDTO;
import com.alkemy.wallet.dto.requestDto.AccountForPostRequestDTO;
import com.alkemy.wallet.dto.responseDto.AccountForPostResponseDTO;
import com.alkemy.wallet.dto.responseDto.BalanceResponseDTO;
import com.alkemy.wallet.model.AccountEntity;

import java.util.List;

public interface AccountService {
    List<AccountDTO> accountsOfUser(Long userId);

    AccountDTO getAccountById(Long idAccountAdressee);

    void pay(AccountEntity receiverAccountIdEntity, Integer amount);

    BalanceResponseDTO getBalance(Long userId);

    void discount(AccountEntity senderAccountIdEntity, Integer amount);

    AccountEntity getAccountEntityById(Long accountId);

    List<AccountEntity> accountsEntityOfUser(Long senderUserId);

    AccountForPostResponseDTO saveAccount(AccountForPostRequestDTO accountForPostRequestDTO);

}
