package com.alkemy.wallet.dto.responseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class UserEntityTransactionsDTO {
    private Long userId;
    private String firstName;
    private List<AccountTransactionsDTO> accountsList = new ArrayList();
}
