package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.dto.AccountDTO;
import com.alkemy.wallet.dto.responseDto.BalanceResponseDTO;
import com.alkemy.wallet.dto.responseDto.FixedTermDepositResponseDTO;
import com.alkemy.wallet.mapping.AccountMapping;
import com.alkemy.wallet.model.Account;
import com.alkemy.wallet.model.Currency;
import com.alkemy.wallet.repository.AccountRepository;
import com.alkemy.wallet.service.service.AccountService;
import com.alkemy.wallet.service.service.FixedTermDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private FixedTermDepositService fixedTermDepositService;

    @Override
    public List<AccountDTO> accountsOfUser(Long userId){
        List<Account> accountsList = accountRepository.accountsOfUser(userId);
        List<AccountDTO> ret = AccountMapping.convertEntityListToDtoList(accountsList);
        return ret;
    }

    @Override
    public AccountDTO getAccountById(Long idAccountAddressee) {
        return AccountMapping.convertEntityToDto(accountRepository.findById(idAccountAddressee).get());
    }

    public Account getAccountEntityById(Long idAccountAddressee) {
        return accountRepository.findById(idAccountAddressee).get();
    }

    @Override
    public void pay(Long receiverAccountId, Integer amount) {
        accountRepository.pay(receiverAccountId, amount);
    }

    @Override
    public void discount(Long senderAccountId, Integer amount) {
        accountRepository.discount(senderAccountId, amount);
    }

    @Override
    public BalanceResponseDTO getBalance(Long userId) {
        List<AccountDTO> accountDTOList = accountsOfUser(userId);

        AccountDTO dollarAccountDTO = accountDTOList.stream().filter(account -> account.getCurrency().equals(Currency.USD)).findAny().get();
        AccountDTO pesosAccountDTO = accountDTOList.stream().filter(account -> account.getCurrency().equals(Currency.ARS)).findAny().get();

        List<FixedTermDepositResponseDTO> fixedTermDepositResponseDTOList = Stream
                .concat(fixedTermDepositService.getFixedTermDepositsDTObyAccountId(dollarAccountDTO.getAccountId()).stream()
                        , fixedTermDepositService.getFixedTermDepositsDTObyAccountId(pesosAccountDTO.getAccountId()).stream())
                .toList();

        BalanceResponseDTO balanceResponseDTO = BalanceResponseDTO.builder()
                .dollarBalance(dollarAccountDTO.getBalance())
                .pesosBalance(pesosAccountDTO.getBalance())
                .fixedTermDepositList(fixedTermDepositResponseDTOList)
                .build();

        return balanceResponseDTO;
    }
}
