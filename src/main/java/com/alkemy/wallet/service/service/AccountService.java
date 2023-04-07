package com.alkemy.wallet.service.service;

import com.alkemy.wallet.dto.AccountDTO;
import com.alkemy.wallet.dto.responseDto.BalanceResponseDTO;
import com.alkemy.wallet.model.AccountEntity;

import java.util.List;

public interface AccountService {
    List<AccountDTO> accountsOfUser(Long userId);

    AccountDTO getAccountById(Long idAccountAdressee);

<<<<<<< HEAD
    void pay(AccountEntity receiverAccountId, Integer amount);

    BalanceResponseDTO getBalance(Long userId);

    void discount(AccountEntity senderAccountId, Integer amount);
=======
    void pay(AccountEntity receiverAccountIdEntity, Integer amount);

    BalanceResponseDTO getBalance(Long userId);

    void discount(AccountEntity senderAccountIdEntity, Integer amount);
>>>>>>> 614614888b5cc84bcec1270039fe67296940fb12

    AccountEntity getAccountEntityById(Long accountId);

    List<AccountEntity> accountsEntityOfUser(Long senderUserId);
}
