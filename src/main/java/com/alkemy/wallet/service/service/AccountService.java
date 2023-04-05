package com.alkemy.wallet.service.service;

import com.alkemy.wallet.dto.AccountDTO;
import com.alkemy.wallet.dto.responseDto.BalanceResponseDTO;
import com.alkemy.wallet.model.Account;

import java.util.List;

public interface AccountService {
    List<AccountDTO> accountsOfUser(Long userId);

    AccountDTO getAccountById(Long idAccountAdressee);

    void pay(Account receiverAccountId, Integer amount);

    BalanceResponseDTO getBalance(Long userId);

    void discount(Account senderAccountId, Integer amount);

    Account getAccountEntityById(Long accountId);

    List<Account> accountsEntityOfUser(Long senderUserId);
}
