package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.dto.AccountDTO;
import com.alkemy.wallet.dto.requestDto.TransactionRequestDTO;
import com.alkemy.wallet.dto.responseDto.UserModelResponseDTO;
import com.alkemy.wallet.model.Currency;
import com.alkemy.wallet.model.Transaction;
import com.alkemy.wallet.model.Type;
import com.alkemy.wallet.repository.TransactionRepository;
import com.alkemy.wallet.service.service.TransactionService;
import com.alkemy.wallet.service.service.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    private UserModelService userModelService;
    @Override
    public void sendUsd(TransactionRequestDTO transactionRequestDTO, Long senderUserId) {
        Long receiverUserId = transactionRequestDTO.getReceiverAccountId();
        UserModelResponseDTO userSender = userModelService.getUserById(senderUserId);
        UserModelResponseDTO userReceiver = userModelService.getUserById(receiverUserId);
        if(userSender == null){
            //TODO throw exception
        }
        if(userReceiver == null){
            //TODO throw exception
        }
        if(receiverUserId.equals(senderUserId)){
            //TODO throw exception
        }
        AccountDTO senderAccount = accountService.accountsOfUser(senderUserId)
                .stream()
                .filter(account -> account.getCurrency().name().equals(Currency.USD.name()))
                .findAny().get();
        AccountDTO receiverAccount = accountService.getAccountById(transactionRequestDTO.getReceiverAccountId());

        generateTransaction(senderAccount, receiverAccount, transactionRequestDTO);
    }

    @Override
    public void editTransactionDescription(Long transactionId, String description) {
        Transaction transaction = transactionRepository.findById(transactionId).get();
        if(transaction == null){
            //TODO throw error...
        }
        transaction.setDescription(description);
        transactionRepository.save(transaction);
    }

    //TODO add throws
    private void generateTransaction(AccountDTO senderAccount, AccountDTO receiverAccount, TransactionRequestDTO transactionRequestDTO){
        checkTransaction(senderAccount, transactionRequestDTO);
        StringBuilder description = new StringBuilder();
        description.append("Se acredito ")
                .append(transactionRequestDTO.getAmount())
                .append(" a la cuenta ")
                .append(receiverAccount.getUser().getFirstName())
                .append(" ")
                .append(receiverAccount.getUser().getLastName())
                .append(" en la fecha ")
                .append(LocalDate.now().toString());

        Transaction newTransaction = Transaction.builder()
                .type(Type.valueOf(transactionRequestDTO.getType()))
                .account(accountService.getAccountEntityById(receiverAccount.getAccountId()))
                .build();
        newTransaction.setDescription(description.toString());

        accountService.pay(receiverAccount.getAccountId(), transactionRequestDTO.getAmount());
        accountService.discount(senderAccount.getAccountId(), transactionRequestDTO.getAmount());
        transactionRepository.save(newTransaction);
    }

    //TODO add throws
    private void checkTransaction(AccountDTO senderAccount, TransactionRequestDTO transactionRequestDTO){
        if(senderAccount.getBalance() < transactionRequestDTO.getAmount()){
            //TODO throw not enough money...
        }
        if(senderAccount.getTransactionLimit() < transactionRequestDTO.getAmount()){
            //TODO throw amount surpass transfer limit
        }
    }
}
