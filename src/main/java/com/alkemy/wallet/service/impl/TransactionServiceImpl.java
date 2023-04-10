package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.dto.requestDto.TransactionRequestDTO;
import com.alkemy.wallet.dto.responseDto.TransactionResponseDTO;
import com.alkemy.wallet.mapping.TransactionMapping;
import com.alkemy.wallet.model.*;
import com.alkemy.wallet.repository.TransactionRepository;
import com.alkemy.wallet.service.service.AccountService;
import com.alkemy.wallet.service.service.TransactionService;
import com.alkemy.wallet.service.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private UserEntityService userEntityService;

    @Override
    public List<TransactionResponseDTO> sendUsd(TransactionRequestDTO transactionRequestDTO, Long senderUserId) throws Exception {
        String currency = Currency.USD.name();
        return send(transactionRequestDTO, senderUserId, currency);
    }

    @Override
    public void editTransactionDescription(Long transactionId, TransactionRequestDTO transactionRequestDTO) throws Exception {
        TransactionEntity transactionEntity = transactionRepository.findById(transactionId).get();
        if (transactionEntity == null) {
            throw new Exception("La transaction especificada no existe.");
        }
        transactionEntity.setDescription(transactionRequestDTO.getDescription());
        transactionRepository.save(transactionEntity);
    }

    public List<TransactionResponseDTO> sendArs(TransactionRequestDTO transactionRequestDTO, Long senderUserId) throws Exception {
        String currency = Currency.ARS.name();
        return send(transactionRequestDTO, senderUserId, currency);
    }

    @Override
    public List<TransactionResponseDTO> trasactionList(long userId) throws Exception {
        UserEntity user = userEntityService.getUserEntityById(userId);
        if (user == null) {
            throw new Exception("El usuario no fue encontrado");
        }
        List<TransactionResponseDTO> transactionResponseDTOList = new ArrayList<>();
        List<AccountEntity> accountEntityUser = user.getAccountsList();
        for (AccountEntity accountEntity : accountEntityUser) {
            transactionResponseDTOList.addAll(TransactionMapping.convertEntityListToDtoList(accountEntity.getTransactionEntityList()));
        }
        return transactionResponseDTOList;
    }

    @Override
    public TransactionResponseDTO makeDeposit(TransactionRequestDTO transactionRequestDTO) throws Exception {
        AccountEntity accountEntity = accountService.getAccountEntityById(transactionRequestDTO.getReceiverAccountId());

        if (transactionRequestDTO.getAmount() <= 0) {
            throw new Exception("El monto depositado debe ser mayor a 0");
        }

        accountService.pay(accountEntity, transactionRequestDTO.getAmount());


        return TransactionMapping.convertEntityToDto(generateDeposit(accountEntity, transactionRequestDTO));
    }


    public List<TransactionResponseDTO> send(TransactionRequestDTO transactionRequestDTO, Long senderUserId, String currency) throws Exception {
        Long receiverUserId = transactionRequestDTO.getReceiverAccountId();
        UserEntity userSender = userEntityService.getUserEntityById(senderUserId);
        UserEntity userReceiver = userEntityService.getUserEntityById(receiverUserId);

        existsUser(userSender);
        existsUser(userReceiver);
        equalUsers(senderUserId, receiverUserId);

        AccountEntity senderAccountEntity = accountService.accountsEntityOfUser(senderUserId)
                .stream()
                .filter(account -> account.getCurrency().name().equals(currency))
                .findAny().get();

        AccountEntity receiverAccountEntity = accountService.getAccountEntityById(transactionRequestDTO.getReceiverAccountId());

        return generateTransaction(senderAccountEntity, receiverAccountEntity, transactionRequestDTO);
    }


    private void equalUsers(Long senderUserId, Long receiverUserId) throws Exception {
        if (senderUserId == receiverUserId) {
            throw new Exception("No se puede hacer transaccion a un mismo usuario");
        }
    }

    private static void existsUser(UserEntity userSender) throws Exception {
        if (userSender == null) {
            throw new Exception("El usuario no existe");
        }
    }

    private List<TransactionResponseDTO> generateTransaction(AccountEntity senderAccountEntity, AccountEntity receiverAccountEntity, TransactionRequestDTO transactionRequestDTO) throws Exception {
        checkTransaction(senderAccountEntity, transactionRequestDTO);

        accountService.pay(receiverAccountEntity, transactionRequestDTO.getAmount());
        accountService.discount(senderAccountEntity, transactionRequestDTO.getAmount());

        List<TransactionEntity> transactionEntityList = new ArrayList<>(2);
        transactionEntityList.add(generateSenderTransaction(senderAccountEntity, receiverAccountEntity, transactionRequestDTO));
        transactionEntityList.add(generateReceiverTransaction(receiverAccountEntity, senderAccountEntity, transactionRequestDTO));

        return TransactionMapping.convertEntityListToDtoList(transactionEntityList);
    }

    private TransactionEntity generateReceiverTransaction(AccountEntity receiverAccountEntity, AccountEntity senderAccountEntity, TransactionRequestDTO transactionRequestDTO) {
        StringBuilder description = new StringBuilder();
        description.append("Se acreditaron ")
                .append(transactionRequestDTO.getAmount())
                .append(" a tu cuenta por parte de ")
                .append(senderAccountEntity.getUser().getFirstName())
                .append(" ")
                .append(senderAccountEntity.getUser().getLastName())
                .append(" en la fecha ")
                .append(LocalDate.now().toString());

        TransactionEntity newTransactionEntity = TransactionEntity.builder()
                .type(Type.INCOME)
                .description(description.toString())
                .accountEntity(accountService.getAccountEntityById(receiverAccountEntity.getAccountId()))
                .build();
        return transactionRepository.save(newTransactionEntity);
    }

    private TransactionEntity generateSenderTransaction(AccountEntity senderAccountEntity, AccountEntity receiverAccountEntity, TransactionRequestDTO transactionRequestDTO) {
        StringBuilder description = new StringBuilder();
        description.append("Se acredito ")
                .append(transactionRequestDTO.getAmount())
                .append(" a la cuenta de")
                .append(receiverAccountEntity.getUser().getFirstName())
                .append(" ")
                .append(receiverAccountEntity.getUser().getLastName())
                .append(" en la fecha ")
                .append(LocalDate.now().toString());

        TransactionEntity newTransactionEntity = TransactionEntity.builder()
                .type(Type.PAYMENT)
                .description(description.toString())
                .accountEntity(accountService.getAccountEntityById(senderAccountEntity.getAccountId()))
                .build();
        return transactionRepository.save(newTransactionEntity);
    }

    private TransactionEntity generateDeposit(AccountEntity accountEntity, TransactionRequestDTO transactionRequestDTO) {
        StringBuilder description = new StringBuilder();
        description.append("Se Deposito")
                .append(transactionRequestDTO.getAmount())
                .append(" a la cuenta de ")
                .append(accountEntity.getUser().getFirstName())
                .append(" ")
                .append(accountEntity.getUser().getLastName())
                .append(" en la fecha ")
                .append(LocalDate.now().toString());

        TransactionEntity newTransactionEntity = TransactionEntity.builder()
                .type(Type.DEPOSIT)
                .description(description.toString())
                .accountEntity(accountService.getAccountEntityById(accountEntity.getAccountId()))
                .build();
        return transactionRepository.save(newTransactionEntity);
    }

    private void checkTransaction(AccountEntity senderAccountEntity, TransactionRequestDTO transactionRequestDTO) throws Exception {
        if (senderAccountEntity.getBalance() < transactionRequestDTO.getAmount()) {
            throw new Exception("No tiene suficiente dinero para realizar la transaccion.");
        }
        if (senderAccountEntity.getTransactionLimit() < transactionRequestDTO.getAmount()) {
            throw new Exception("El valor indicado para la transaccion excede su limite de cuenta.");
        }
    }

    @Override
    public ResponseEntity<TransactionResponseDTO> trasactionForId(long transactionId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(TransactionMapping
                        .convertEntityToDto(transactionRepository.findById(transactionId).get()));
    }
}
