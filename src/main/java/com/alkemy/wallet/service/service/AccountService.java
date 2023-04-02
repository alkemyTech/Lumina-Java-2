package com.alkemy.wallet.service.service;

import com.alkemy.wallet.dto.AccountDTO;
import com.alkemy.wallet.dto.responseDto.BalanceResponseDTO;

import java.util.List;

public interface AccountService {
    List<AccountDTO> accountsOfUser(Long userId);

    AccountDTO getAccountById(Long idAccountAdressee);

    void pay(Long receiverAccountId, Integer amount);

    void discount(Long senderAccountId, Integer amount);

    BalanceResponseDTO getBalance(Long userId);
}
