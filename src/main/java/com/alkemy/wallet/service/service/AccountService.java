package com.alkemy.wallet.service.service;

import com.alkemy.wallet.dto.AccountDTO;
import com.alkemy.wallet.dto.responseDto.BalanceResponseDTO;
import com.alkemy.wallet.model.AccountEntity;

import java.util.List;

public interface AccountService {
    List<AccountDTO> accountsOfUser(Long userId);

    AccountDTO getAccountById(Long idAccountAdressee);

    void pay(AccountEntity receiverAccountId, Integer amount);

    BalanceResponseDTO getBalance(Long userId);

    void discount(AccountEntity senderAccountId, Integer amount);

    AccountEntity getAccountEntityById(Long accountId);

    List<AccountEntity> accountsEntityOfUser(Long senderUserId);
}
