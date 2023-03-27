package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.entity.Account;
import com.alkemy.wallet.repository.AccountRepository;
import com.alkemy.wallet.service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> accountsOfUser(Long userId){
        return accountRepository.accountsOfUser(userId);
    }
}
