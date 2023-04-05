package com.alkemy.wallet.service.service;

import com.alkemy.wallet.dto.responseDto.FixedTermDepositResponseDTO;

import java.util.List;


public interface FixedTermDepositService {
    List<FixedTermDepositResponseDTO> getFixedTermDepositsDTObyAccountId(Long accountId);
}
