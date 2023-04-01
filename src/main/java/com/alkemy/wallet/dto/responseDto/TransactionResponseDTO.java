package com.alkemy.wallet.dto.responseDto;

import com.alkemy.wallet.model.Account;
import com.alkemy.wallet.model.Type;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class TransactionResponseDTO {
//    private Long transactionId;
    private Integer amount;
    private String type;
    private String description;
    private Long accountId;
    private LocalDate transactionDate;
}
