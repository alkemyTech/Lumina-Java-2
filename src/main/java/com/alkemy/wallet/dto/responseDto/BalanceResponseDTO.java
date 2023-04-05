package com.alkemy.wallet.dto.responseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class BalanceResponseDTO {
    private Double dollarBalance;
    private Double pesosBalance;
    private List<FixedTermDepositResponseDTO> fixedTermDepositList;
}
