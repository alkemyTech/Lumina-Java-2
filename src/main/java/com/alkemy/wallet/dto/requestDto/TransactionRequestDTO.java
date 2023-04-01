package com.alkemy.wallet.dto.requestDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TransactionRequestDTO {
    private Long receiverAccountId;
    private Integer amount;
    private String type;
}
