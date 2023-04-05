package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.dto.responseDto.FixedTermDepositResponseDTO;
import com.alkemy.wallet.mapping.FixedTermDepositMapping;
import com.alkemy.wallet.repository.FixedTermDepositRepository;
import com.alkemy.wallet.service.service.FixedTermDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FixedTermDepositServiceImpl implements FixedTermDepositService {
    @Autowired
    FixedTermDepositRepository fixedTermDepositRepository;

    @Override
    public List<FixedTermDepositResponseDTO> getFixedTermDepositsDTObyAccountId(Long accountId) {
        return FixedTermDepositMapping.convertEntityListToDtoList(fixedTermDepositRepository.getFixedTermDepositDTObyAccountId(accountId));
    }
}
