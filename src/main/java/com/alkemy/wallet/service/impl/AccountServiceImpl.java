package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.Exception.InvalidResourceException;
import com.alkemy.wallet.dto.AccountDTO;
import com.alkemy.wallet.dto.requestDto.AccountForPostRequestDTO;
import com.alkemy.wallet.dto.responseDto.AccountForPostResponseDTO;
import com.alkemy.wallet.dto.responseDto.BalanceResponseDTO;
import com.alkemy.wallet.mapping.AccountForPostMapping;
import com.alkemy.wallet.mapping.AccountMapping;
import com.alkemy.wallet.mapping.FixedTermDepositMapping;
import com.alkemy.wallet.model.AccountEntity;
import com.alkemy.wallet.model.Currency;
import com.alkemy.wallet.model.FixedTermDepositEntity;
import com.alkemy.wallet.model.UserEntity;
import com.alkemy.wallet.repository.AccountRepository;
import com.alkemy.wallet.service.service.AccountService;
import com.alkemy.wallet.service.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserEntityService userEntityService;

    @Override
    public List<AccountDTO> accountsOfUser(Long userId) {
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
    public void pay(AccountEntity receiverAccountEntity, Integer amount) {
        receiverAccountEntity.setBalance(receiverAccountEntity.getBalance() + amount);
        accountRepository.save(receiverAccountEntity);
    }

    @Override
    public void discount(AccountEntity senderAccountEntity, Integer amount) {
        senderAccountEntity.setBalance(senderAccountEntity.getBalance() - amount);
        accountRepository.save(senderAccountEntity);
    }

    @Override
    public BalanceResponseDTO getBalance(Long userId) {
        List<AccountEntity> accountList = accountsEntityOfUser(userId);

        AccountEntity dollarAccount = accountList.stream().filter(account -> account.getCurrency().equals(Currency.USD)).findAny().get();
        AccountEntity pesosAccount = accountList.stream().filter(account -> account.getCurrency().equals(Currency.ARS)).findAny().get();

        List<FixedTermDepositEntity> fixedTermDepositList = Stream
                .concat(dollarAccount.getFixedTermDepositEntityList().stream(), pesosAccount.getFixedTermDepositEntityList().stream())
                .toList();

        BalanceResponseDTO balanceResponseDTO = BalanceResponseDTO.builder()
                .dollarBalance(dollarAccount.getBalance())
                .pesosBalance(pesosAccount.getBalance())
                .fixedTermDepositList(FixedTermDepositMapping.convertEntityListToDtoList(fixedTermDepositList))
                .build();

        return balanceResponseDTO;
    }

    @Override
    public AccountForPostResponseDTO saveAccount(AccountForPostRequestDTO accountForPostRequestDTO) {

        AccountEntity newAccount = AccountForPostMapping.convertDtoToEntity(accountForPostRequestDTO);

        UserEntity newUser = userEntityService.getUserEntityById(accountForPostRequestDTO.getUser().getUserId());

        Boolean found = Boolean.FALSE;

        for (AccountEntity account : newUser.getAccountsList()) {
            if (account.getCurrency().name().equals(accountForPostRequestDTO.getCurrency())) {

                newAccount = account;

                found = Boolean.TRUE;
            }
        }


        if (!found) {
            if (accountForPostRequestDTO.getCurrency().equals(Currency.ARS.name())) {
                newAccount.setCurrency(Currency.ARS);
                newAccount.setTransactionLimit(300000d);
                newUser.getAccountsList().add(newAccount);

            } else if (accountForPostRequestDTO.getCurrency().equals(Currency.USD.name())) {

                newAccount.setCurrency(Currency.USD);
                newAccount.setTransactionLimit(1000d);
                newUser.getAccountsList().add(newAccount);
            } else {
                throw new InvalidResourceException("La moneda enviada en la peticion no existe");
            }

        }


        newAccount.setBalance(0d);
        newAccount.setUser(newUser);

        return AccountForPostMapping.convertEntityToDto(accountRepository.save(newAccount));
    }

}
