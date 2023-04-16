package com.alkemy.wallet.dto.responseDto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@Builder
public class AccountTransactionsDTO {
    private Long accountId;
    private List<Transactions4AccountDTO> transactionEntityList = new ArrayList();

}
