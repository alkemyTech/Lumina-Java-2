package com.alkemy.wallet.dto.responseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class Transactions4AccountDTO {

    private Integer amount;
    private String type;
    private String description;
    private LocalDate transactionDate;
}
