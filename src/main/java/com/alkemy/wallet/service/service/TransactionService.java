package com.alkemy.wallet.service.service;

import com.alkemy.wallet.dto.requestDto.TransactionRequestDTO;

public interface TransactionService {
    void sendUsd(TransactionRequestDTO transactionRequestDTO, Long userId);

    void editTransactionDescription(Long transactionId, TransactionRequestDTO transactionRequestDTO) throws Exception;
}
