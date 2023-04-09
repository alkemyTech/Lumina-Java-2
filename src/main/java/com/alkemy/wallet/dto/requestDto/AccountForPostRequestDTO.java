package com.alkemy.wallet.dto.requestDto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
@Builder
public class AccountForPostRequestDTO {
    private String currency;

    private Double transactionLimit;

    private Double balance;

    private UserEntityRequestDTO user;

    private LocalDate creationDate;


    private LocalDate updateDate;

}
