package com.alkemy.wallet.mapping;

import com.alkemy.wallet.dto.responseDto.FixedTermDepositResponseDTO;
import com.alkemy.wallet.model.FixedTermDepositEntity;

import java.util.ArrayList;
import java.util.List;

public class FixedTermDepositMapping {
    public static FixedTermDepositResponseDTO convertEntityToDto(FixedTermDepositEntity fixedTermDepositEntity){
        return FixedTermDepositResponseDTO.builder()
                .closingDate(fixedTermDepositEntity.getClosingDate())
                .accountId(fixedTermDepositEntity.getAccountEntity().getAccountId())
                .interest(fixedTermDepositEntity.getInterest())
                .amount(fixedTermDepositEntity.getAmount())
                .creationDate(fixedTermDepositEntity.getCreationDate())
                .build();
    }

    public static List<FixedTermDepositResponseDTO> convertEntityListToDtoList(List<FixedTermDepositEntity> fixedTermDepositEntityList){
        List<FixedTermDepositResponseDTO> ret = new ArrayList<>(fixedTermDepositEntityList.size());
        for(FixedTermDepositEntity fixedTermDepositEntity : fixedTermDepositEntityList){
            ret.add(convertEntityToDto(fixedTermDepositEntity));
        }
        return ret;
    }
}
