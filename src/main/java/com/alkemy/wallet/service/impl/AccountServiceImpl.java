package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.dto.AccountDTO;
import com.alkemy.wallet.mapping.AccountMapping;
import com.alkemy.wallet.model.Account;
import com.alkemy.wallet.repository.AccountRepository;
import com.alkemy.wallet.service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<AccountDTO> accountsOfUser(Long userId){
        List<Account> accountsList = accountRepository.accountsOfUser(userId);
        List<AccountDTO> ret = AccountMapping.convertEntityListToDtoList(accountsList);
        return ret;
    }

    @Override
    public AccountDTO getAccountById(Long idAccountAddressee) {
        return AccountMapping.convertEntityToDto(accountRepository.findById(idAccountAddressee).get());
    }

    public Account getAccountEntityById(Long idAccountAddressee) {
        return accountRepository.findById(idAccountAddressee).get();
    }

    @Override
    public void pay(Long receiverAccountId, Integer amount) {
        accountRepository.pay(receiverAccountId, amount);
    }

    @Override
    public void discount(Long senderAccountId, Integer amount) {
        accountRepository.discount(senderAccountId, amount);
    }
}
