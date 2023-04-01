package com.alkemy.wallet.service.service;

import com.alkemy.wallet.dto.AccountDTO;

import java.util.List;

public interface AccountService {
    List<AccountDTO> accountsOfUser(Long userId);

    AccountDTO getAccount(Long idSender);
}
