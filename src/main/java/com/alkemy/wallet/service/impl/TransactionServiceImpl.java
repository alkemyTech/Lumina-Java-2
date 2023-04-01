package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.dto.AccountDTO;
import com.alkemy.wallet.dto.requestDto.TransactionDTORequest;
import com.alkemy.wallet.repository.AccountRepository;
import com.alkemy.wallet.service.service.AccountService;
import com.alkemy.wallet.service.service.TransactionService;
import com.alkemy.wallet.service.service.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserModelService userModelService;
    @Override
    public void sendUsd(TransactionDTORequest transactionRequestDTO, Long idSender) {
        AccountDTO account = accountService.getAccount(idSender);
        if(account.getUser().getUserId().equals(idSender)){
        }
        UserModelDTO userSender = userModelService.getUserById(idSender);
        UserModelDTO userReceiver = userModelService.getUserById(account.getUser().getUserId());



    }
}
