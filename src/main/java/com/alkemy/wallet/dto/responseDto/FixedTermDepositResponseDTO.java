package com.alkemy.wallet.dto.responseDto;

import com.alkemy.wallet.model.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class FixedTermDepositResponseDTO {
    private Double amount;
    private Long accountId;
    private Double interest;
    private LocalDate creationDate;
    private LocalDate closingDate;
}
