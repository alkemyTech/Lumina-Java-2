package com.alkemy.wallet.service.service;

import com.alkemy.wallet.dto.requestDto.FixedTermDepositRequestDTO;
import com.alkemy.wallet.dto.responseDto.FixedTermDepositResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface FixedTermDepositService {
    List<FixedTermDepositResponseDTO> getFixedTermDepositsDTObyAccountId(Long accountId);

    ResponseEntity<?> createFixedDeposit(Long accountId, Long userId, FixedTermDepositRequestDTO fixedTermDepositRequestDTO) throws Exception;
}
