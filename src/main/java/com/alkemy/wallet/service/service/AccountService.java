package com.alkemy.wallet.service.service;

import com.alkemy.wallet.dto.AccountDTO;
import com.alkemy.wallet.model.Account;
import org.springframework.beans.PropertyValues;

import java.util.List;

public interface AccountService {
    List<AccountDTO> accountsOfUser(Long userId);

    AccountDTO getAccountById(Long idAccountAdressee);

    void pay(Account receiverAccountId, Integer amount);

    void discount(Account senderAccountId, Integer amount);

    Account getAccountEntityById(Long accountId);

    List<Account> accountsEntityOfUser(Long senderUserId);
}
