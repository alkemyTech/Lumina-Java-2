package com.alkemy.wallet.dto;

import com.alkemy.wallet.model.Currency;
import com.alkemy.wallet.model.FixedTermDepositEntity;
import com.alkemy.wallet.model.TransactionEntity;
import com.alkemy.wallet.model.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class AccountDTO {
    private Long accountId;
    private Currency currency;
    private Double transactionLimit;
    private Double balance;
    private UserEntity user;
    private LocalDate creationDate;
    private LocalDate updateDate;
    private Boolean softDelete;
    private List<TransactionEntity> transactionEntityList;
    private List<FixedTermDepositEntity> fixedTermDepositEntityList;
}
