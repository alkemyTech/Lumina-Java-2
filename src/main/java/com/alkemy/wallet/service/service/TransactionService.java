package com.alkemy.wallet.service.service;

import com.alkemy.wallet.dto.requestDto.TransactionDTORequest;

import javax.persistence.EnumType;
import java.util.Enumeration;

public interface TransactionService {
    void sendUsd(TransactionDTORequest transactionRequestDTO, Long idSender);
}
