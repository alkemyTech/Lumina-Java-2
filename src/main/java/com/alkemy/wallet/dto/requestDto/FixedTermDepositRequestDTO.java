package com.alkemy.wallet.dto.requestDto;

import com.alkemy.wallet.model.AccountEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class FixedTermDepositRequestDTO {
    private Double amount;
    private LocalDate closingDate;
    private AccountEntity account;
}
