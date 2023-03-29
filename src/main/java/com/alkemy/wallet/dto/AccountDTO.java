package com.alkemy.wallet.dto;

import com.alkemy.wallet.model.Currency;
import com.alkemy.wallet.model.FixedTermDeposit;
import com.alkemy.wallet.model.Transaction;
import com.alkemy.wallet.model.UserModel;
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
    private UserModel user;
    private LocalDate creationDate;
    private LocalDate updateDate;
    private Boolean softDelete;
    private List<Transaction> transactionList;
    private List<FixedTermDeposit> fixedTermDepositList;
}
