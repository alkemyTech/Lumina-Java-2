package com.alkemy.wallet.mapping;

import com.alkemy.wallet.dto.responseDto.FixedTermDepositResponseDTO;
import com.alkemy.wallet.model.FixedTermDepositEntity;

import java.util.ArrayList;
import java.util.List;

public class FixedTermDepositMapping {
    public static FixedTermDepositResponseDTO convertEntityToDto(FixedTermDepositEntity fixedTermDeposit){
        return FixedTermDepositResponseDTO.builder()
                .closingDate(fixedTermDeposit.getClosingDate())
                .accountId(fixedTermDeposit.getAccount().getAccountId())
                .interest(fixedTermDeposit.getInterest())
                .amount(fixedTermDeposit.getAmount())
                .creationDate(fixedTermDeposit.getCreationDate())
                .build();
    }

    public static List<FixedTermDepositResponseDTO> convertEntityListToDtoList(List<FixedTermDepositEntity> fixedTermDepositList){
        List<FixedTermDepositResponseDTO> ret = new ArrayList<>(fixedTermDepositList.size());
        for(FixedTermDepositEntity fixedTermDeposit : fixedTermDepositList){
            ret.add(convertEntityToDto(fixedTermDeposit));
        }
        return ret;
    }
}
