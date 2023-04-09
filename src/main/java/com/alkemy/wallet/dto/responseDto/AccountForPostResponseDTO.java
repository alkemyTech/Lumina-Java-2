package com.alkemy.wallet.dto.responseDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Data
@Getter
@Setter
@Builder
public class AccountForPostResponseDTO {

    private Long accountId;
    private String currency;

    private Double transactionLimit;

    private Double balance;

    @JsonIgnoreProperties(value ="accountsList")
    private UserEntityResponseDTO user;

    private LocalDate creationDate;

    private LocalDate updateDate;

}
