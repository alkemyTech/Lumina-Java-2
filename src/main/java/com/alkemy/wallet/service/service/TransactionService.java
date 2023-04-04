package com.alkemy.wallet.service.service;

import com.alkemy.wallet.dto.requestDto.TransactionRequestDTO;
import com.alkemy.wallet.dto.responseDto.TransactionResponseDTO;
import com.alkemy.wallet.model.UserModel;

public interface TransactionService {
    TransactionResponseDTO sendUsd(TransactionRequestDTO transactionRequestDTO, Long userId) throws Exception;

    TransactionResponseDTO sendArs(TransactionRequestDTO transactionRequestDTO, Long userId) throws Exception;
}
