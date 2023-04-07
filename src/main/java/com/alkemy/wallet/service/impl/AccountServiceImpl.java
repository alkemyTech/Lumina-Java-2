package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.dto.AccountDTO;
import com.alkemy.wallet.dto.responseDto.BalanceResponseDTO;
import com.alkemy.wallet.mapping.AccountMapping;
import com.alkemy.wallet.mapping.FixedTermDepositMapping;
import com.alkemy.wallet.model.AccountEntity;
import com.alkemy.wallet.model.Currency;
import com.alkemy.wallet.model.FixedTermDepositEntity;
import com.alkemy.wallet.repository.AccountRepository;
import com.alkemy.wallet.service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<AccountDTO> accountsOfUser(Long userId){
        List<AccountEntity> accountsList = accountRepository.accountsOfUser(userId);
        List<AccountDTO> ret = AccountMapping.convertEntityListToDtoList(accountsList);
        return ret;
    }

    @Override
    public AccountDTO getAccountById(Long idAccountAddressee) {
        return AccountMapping.convertEntityToDto(accountRepository.findById(idAccountAddressee).get());
    }

    public AccountEntity getAccountEntityById(Long idAccountAddressee) {
        return accountRepository.findById(idAccountAddressee).get();
    }

    @Override
    public List<AccountEntity> accountsEntityOfUser(Long userId) {
        List<AccountEntity> ret = accountRepository.accountsOfUser(userId);
        return ret;
    }

    @Override
    public void pay(AccountEntity receiverAccount, Integer amount) {
        receiverAccount.setBalance(receiverAccount.getBalance() + amount);
        accountRepository.save(receiverAccount);
    }

    @Override
    public void discount(AccountEntity senderAccount, Integer amount) {
        senderAccount.setBalance(senderAccount.getBalance() - amount);
        accountRepository.save(senderAccount);
    }

    @Override
    public BalanceResponseDTO getBalance(Long userId) {
        List<AccountEntity> accountList = accountsEntityOfUser(userId);

        AccountEntity dollarAccount = accountList.stream().filter(account -> account.getCurrency().equals(Currency.USD)).findAny().get();
        AccountEntity pesosAccount = accountList.stream().filter(account -> account.getCurrency().equals(Currency.ARS)).findAny().get();

        List<FixedTermDepositEntity> fixedTermDepositList = Stream
                .concat(dollarAccount.getFixedTermDepositList().stream(), pesosAccount.getFixedTermDepositList().stream())
                .toList();

        BalanceResponseDTO balanceResponseDTO = BalanceResponseDTO.builder()
                .dollarBalance(dollarAccount.getBalance())
                .pesosBalance(pesosAccount.getBalance())
                .fixedTermDepositList(FixedTermDepositMapping.convertEntityListToDtoList(fixedTermDepositList))
                .build();

        return balanceResponseDTO;
    }
}
