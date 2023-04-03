package com.alkemy.wallet.service.service;

import com.alkemy.wallet.dto.requestDto.TransactionRequestDTO;
import com.alkemy.wallet.model.UserModel;

public interface TransactionService {
    void sendUsd(TransactionRequestDTO transactionRequestDTO, Long userId) throws Exception;
}
