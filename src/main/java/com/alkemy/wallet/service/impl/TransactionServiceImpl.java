package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.dto.AccountDTO;
import com.alkemy.wallet.dto.requestDto.TransactionRequestDTO;
import com.alkemy.wallet.dto.responseDto.UserModelResponseDTO;
import com.alkemy.wallet.model.Currency;
import com.alkemy.wallet.model.Transaction;
import com.alkemy.wallet.model.Type;
import com.alkemy.wallet.model.UserModel;
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
    public void sendUsd(TransactionRequestDTO transactionRequestDTO, Long senderUserId) throws Exception {
        Long receiverUserId = transactionRequestDTO.getReceiverAccountId();
        UserModel userSender = userModelService.getUserEntityById(senderUserId);
        UserModel userReceiver = userModelService.getUserEntityById(receiverUserId);

        existsUser(userSender);
        existsUser(userReceiver);
        equalUsers(senderUserId, receiverUserId);

        AccountDTO senderAccount = accountService.accountsOfUser(senderUserId)
                .stream()
                .filter(account -> account.getCurrency().name().equals(Currency.USD.name()))
                .findAny().get();

        AccountDTO receiverAccount = accountService.getAccountById(transactionRequestDTO.getReceiverAccountId());

        generateTransaction(senderAccount, receiverAccount, transactionRequestDTO);
    }

    private void equalUsers(Long senderUserId, Long receiverUserId) throws Exception {
        if(senderUserId == receiverUserId){
            throw new Exception("No se puede hacer transaccion a un mismo usuario");
        }
    }

    private static void existsUser(UserModel userSender) throws Exception {
        if  (userSender == null){
            throw new Exception("El usuario no existe");
        }
    }

    //TODO add throws
    private void generateTransaction(AccountDTO senderAccount, AccountDTO receiverAccount, TransactionRequestDTO transactionRequestDTO) throws Exception {
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
                .description(description.toString())
                .account(accountService.getAccountEntityById(receiverAccount.getAccountId()))
                .build();

        accountService.pay(receiverAccount.getAccountId(), transactionRequestDTO.getAmount());
        accountService.discount(senderAccount.getAccountId(), transactionRequestDTO.getAmount());
        
        transactionRepository.save(newTransaction);
    }

    //TODO add throws
    private void checkTransaction(AccountDTO senderAccount, TransactionRequestDTO transactionRequestDTO) throws Exception {
        if(senderAccount.getBalance() < transactionRequestDTO.getAmount()){
            throw new Exception("No tiene sufiente plata para la transaccion.");
        }
        if(senderAccount.getTransactionLimit() < transactionRequestDTO.getAmount()){
            throw new Exception("El valor indicado para la transaccion excede su limite de cuenta.");
        }
    }
}
