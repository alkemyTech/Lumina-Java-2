package com.alkemy.wallet.service.service;

import com.alkemy.wallet.dto.requestDto.TransactionRequestDTO;
import com.alkemy.wallet.dto.responseDto.TransactionResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TransactionService {
    void editTransactionDescription(Long transactionId, TransactionRequestDTO transactionRequestDTO) throws Exception;
    List<TransactionResponseDTO> sendUsd(TransactionRequestDTO transactionRequestDTO, Long userId) throws Exception;

    List<TransactionResponseDTO> sendArs(TransactionRequestDTO transactionRequestDTO, Long userId) throws Exception;

    List<TransactionResponseDTO> trasactionList(long userId) throws Exception;

    TransactionResponseDTO makeDeposit(TransactionRequestDTO transactionRequestDTO) throws Exception;

    ResponseEntity<TransactionResponseDTO> trasactionForId(long transactionId);

    ResponseEntity<TransactionResponseDTO> createTransactionPayment(TransactionRequestDTO transactionRequestDTO);


}
