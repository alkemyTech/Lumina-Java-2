package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.dto.requestDto.TransactionRequestDTO;
import com.alkemy.wallet.dto.responseDto.TransactionResponseDTO;
import com.alkemy.wallet.mapping.TransactionMapping;
import com.alkemy.wallet.model.*;
import com.alkemy.wallet.repository.TransactionRepository;
import com.alkemy.wallet.service.service.AccountService;
import com.alkemy.wallet.service.service.TransactionService;
import com.alkemy.wallet.service.service.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountService accountService;

    @Autowired
    private UserModelService userModelService;
    @Override
    public List<TransactionResponseDTO> sendUsd(TransactionRequestDTO transactionRequestDTO, Long senderUserId) throws Exception {
        String currency = Currency.USD.name();
        return send(transactionRequestDTO,senderUserId, currency);
    }

    @Override
    public void editTransactionDescription(Long transactionId, TransactionRequestDTO transactionRequestDTO) throws Exception{
        Transaction transaction = transactionRepository.findById(transactionId).get();
        if(transaction == null){
            throw new Exception("La transaction especificada no existe.");
        }
        transaction.setDescription(transactionRequestDTO.getDescription());
        transactionRepository.save(transaction);
    }
    public List<TransactionResponseDTO> sendArs(TransactionRequestDTO transactionRequestDTO, Long senderUserId) throws Exception {
        String currency = Currency.ARS.name();
        return send(transactionRequestDTO,senderUserId,currency);
    }

    @Override
    public List<TransactionResponseDTO> trasactionList(long userId) throws Exception {
        UserModel user = userModelService.getUserEntityById(userId);
        if (user == null){
            throw new Exception("El usuario no fue encontrado");
        }
        List<TransactionResponseDTO> transactionResponseDTOList = new ArrayList<>();
        List<Account> accountUser = user.getAccountsList();
        for (Account account:accountUser
             ) {
         transactionResponseDTOList.addAll(TransactionMapping.convertEntityListToDtoList( account.getTransactionList()));

        }
        return transactionResponseDTOList;
    }

    @Override
    public TransactionResponseDTO makeDeposit(TransactionRequestDTO transactionRequestDTO) throws Exception {
        Account account = accountService.getAccountEntityById(transactionRequestDTO.getReceiverAccountId());

        if (transactionRequestDTO.getAmount() <= 0){
            throw new Exception("El monto depositado debe ser mayor a 0");
        }

        accountService.pay(account,transactionRequestDTO.getAmount());


        return TransactionMapping.convertEntityToDto(generateDeposit(account,transactionRequestDTO));
    }


    public List<TransactionResponseDTO> send(TransactionRequestDTO transactionRequestDTO, Long senderUserId,String currency) throws Exception {
        Long receiverUserId = transactionRequestDTO.getReceiverAccountId();
        UserModel userSender = userModelService.getUserEntityById(senderUserId);
        UserModel userReceiver = userModelService.getUserEntityById(receiverUserId);


        existsUser(userSender);
        existsUser(userReceiver);
        equalUsers(senderUserId, receiverUserId);


        Account senderAccount = accountService.accountsEntityOfUser(senderUserId)
                .stream()
                .filter(account -> account.getCurrency().name().equals(currency))
                .findAny().get();

        Account receiverAccount = accountService.getAccountEntityById(transactionRequestDTO.getReceiverAccountId());

        return generateTransaction(senderAccount, receiverAccount, transactionRequestDTO);
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

    private List<TransactionResponseDTO> generateTransaction(Account senderAccount, Account receiverAccount, TransactionRequestDTO transactionRequestDTO) throws Exception {
        checkTransaction(senderAccount, transactionRequestDTO);

        accountService.pay(receiverAccount, transactionRequestDTO.getAmount());
        accountService.discount(senderAccount, transactionRequestDTO.getAmount());

        List<Transaction> transactionList = new ArrayList<>(2);
        transactionList.add(generateSenderTransaction(senderAccount, receiverAccount, transactionRequestDTO));
        transactionList.add(generateReceiverTransaction(receiverAccount, senderAccount, transactionRequestDTO));

        return TransactionMapping.convertEntityListToDtoList(transactionList);
    }

    private Transaction generateReceiverTransaction(Account receiverAccount, Account senderAccount, TransactionRequestDTO transactionRequestDTO) {
        StringBuilder description = new StringBuilder();
        description.append("Se acreditaron ")
                .append(transactionRequestDTO.getAmount())
                .append(" a tu cuenta por parte de ")
                .append(senderAccount.getUser().getFirstName())
                .append(" ")
                .append(senderAccount.getUser().getLastName())
                .append(" en la fecha ")
                .append(LocalDate.now().toString());

        Transaction newTransaction = Transaction.builder()
                .type(Type.INCOME)
                .description(description.toString())
                .account(accountService.getAccountEntityById(receiverAccount.getAccountId()))
                .build();
        return transactionRepository.save(newTransaction);
    }

    private Transaction generateSenderTransaction(Account senderAccount, Account receiverAccount, TransactionRequestDTO transactionRequestDTO) {
        StringBuilder description = new StringBuilder();
        description.append("Se acredito ")
                .append(transactionRequestDTO.getAmount())
                .append(" a la cuenta de")
                .append(receiverAccount.getUser().getFirstName())
                .append(" ")
                .append(receiverAccount.getUser().getLastName())
                .append(" en la fecha ")
                .append(LocalDate.now().toString());

        Transaction newTransaction = Transaction.builder()
                .type(Type.PAYMENT)
                .description(description.toString())
                .account(accountService.getAccountEntityById(senderAccount.getAccountId()))
                .build();
        return transactionRepository.save(newTransaction);
    }

    private Transaction generateDeposit(Account account,TransactionRequestDTO transactionRequestDTO){
        StringBuilder description = new StringBuilder();
        description.append("Se Deposito")
                .append(transactionRequestDTO.getAmount())
                .append(" a la cuenta de ")
                .append(account.getUser().getFirstName())
                .append(" ")
                .append(account.getUser().getLastName())
                .append(" en la fecha ")
                .append(LocalDate.now().toString());

        Transaction newTransaction = Transaction.builder()
                .type(Type.DEPOSIT)
                .description(description.toString())
                .account(accountService.getAccountEntityById(account.getAccountId()))
                .build();
        return transactionRepository.save(newTransaction);
    }

    private void checkTransaction(Account senderAccount, TransactionRequestDTO transactionRequestDTO) throws Exception {
        if(senderAccount.getBalance() < transactionRequestDTO.getAmount()){
            throw new Exception("No tiene suficiente dinero para realizar la transaccion.");
        }
        if(senderAccount.getTransactionLimit() < transactionRequestDTO.getAmount()){
            throw new Exception("El valor indicado para la transaccion excede su limite de cuenta.");
        }
    }
}
