package com.alkemy.wallet.service.service;

import com.alkemy.wallet.dto.AccountDTO;
import com.alkemy.wallet.dto.requestDto.AccountForPostRequestDTO;
import com.alkemy.wallet.dto.responseDto.AccountForPostResponseDTO;
import com.alkemy.wallet.dto.responseDto.BalanceResponseDTO;
import com.alkemy.wallet.dto.responseDto.UserEntityResponseDTO;
import com.alkemy.wallet.model.AccountEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountService {
    List<AccountDTO> accountsOfUser(Long userId);

    AccountDTO getAccountById(Long idAccountAdressee);

    void pay(AccountEntity receiverAccountId, Integer amount);

    BalanceResponseDTO getBalance(Long userId);

    void discount(AccountEntity senderAccountId, Integer amount);

    AccountEntity getAccountEntityById(Long accountId);

    List<AccountEntity> accountsEntityOfUser(Long senderUserId);

    AccountForPostResponseDTO saveAccount(AccountForPostRequestDTO accountForPostRequestDTO);

    void editTransactionLimit(Long idAccount, AccountForPostRequestDTO accountForPostRequestDTO) throws Exception;

    Page<AccountForPostResponseDTO> getAccountList(Pageable pageable) throws Exception;
}
