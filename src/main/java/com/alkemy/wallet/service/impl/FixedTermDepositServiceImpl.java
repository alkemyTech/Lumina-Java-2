package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.dto.requestDto.FixedTermDepositRequestDTO;
import com.alkemy.wallet.dto.responseDto.FixedTermDepositResponseDTO;
import com.alkemy.wallet.mapping.FixedTermDepositMapping;
import com.alkemy.wallet.model.AccountEntity;
import com.alkemy.wallet.model.FixedTermDepositEntity;
import com.alkemy.wallet.model.UserEntity;
import com.alkemy.wallet.repository.FixedTermDepositRepository;
import com.alkemy.wallet.service.service.AccountService;
import com.alkemy.wallet.service.service.FixedTermDepositService;
import com.alkemy.wallet.service.service.UserEntityService;
import com.alkemy.wallet.utils.SimpleInterestCalculator;
import com.alkemy.wallet.utils.SimpleInterestPerDayCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
public class FixedTermDepositServiceImpl implements FixedTermDepositService {
    private Float interestRate = 0.5F;
    @Autowired
    FixedTermDepositRepository fixedTermDepositRepository;

    @Autowired
    UserEntityService userEntityService;
    @Autowired
    AccountService accountService;

    @Override
    public List<FixedTermDepositResponseDTO> getFixedTermDepositsDTObyAccountId(Long accountId) {
        return FixedTermDepositMapping.convertEntityListToDtoList(fixedTermDepositRepository.getFixedTermDepositDTObyAccountId(accountId));
    }

    @Override
    public ResponseEntity<?> createFixedDeposit(Long accountId, Long userId, FixedTermDepositRequestDTO fixedTermDepositRequestDTO) throws Exception {
        UserEntity user = userEntityService.getUserEntityById(userId);
        AccountEntity account = accountService.getAccountEntityById(accountId);
        if(user == null || account == null){
            throw new Exception("Usuario o cuenta no existente.");
        }
        checkEnoughBalance(account, fixedTermDepositRequestDTO.getAmount());
        checkCorrectClosingDate(fixedTermDepositRequestDTO.getClosingDate());

        accountService.discount(account, fixedTermDepositRequestDTO.getAmount().intValue());
        generateFixedDeposit(account, user, fixedTermDepositRequestDTO);

        return null;
    }

    private void generateFixedDeposit(AccountEntity account, UserEntity user, FixedTermDepositRequestDTO fixedTermDepositRequestDTO) {
        SimpleInterestCalculator interestCalculator = new SimpleInterestPerDayCalculator();
        FixedTermDepositEntity fixedTermDeposit = FixedTermDepositEntity.builder()
                .creationDate(LocalDate.now())
                .interest(interestCalculator.calculate(fixedTermDepositRequestDTO.getAmount(),
                        this.interestRate,
                        (int) ChronoUnit.DAYS.between(LocalDate.now(), fixedTermDepositRequestDTO.getClosingDate())))
                .closingDate(fixedTermDepositRequestDTO.getClosingDate())
                .amount(fixedTermDepositRequestDTO.getAmount())
                .accountEntity(account)
                .build();
        fixedTermDepositRepository.save(fixedTermDeposit);
    }

    private void checkCorrectClosingDate(LocalDate closingDate) throws Exception {
        checkCorrectDate(closingDate);
        long fixedDepositDuration = ChronoUnit.DAYS.between(LocalDate.now(), closingDate);
        if(fixedDepositDuration < 30){
            throw new Exception("No se pueden realizar plazos fijos menores a 30 dias");
        }
    }
    private void checkCorrectDate(LocalDate closingDate) throws Exception {
        if(LocalDate.now().isAfter(closingDate)){
            throw new Exception("La fecha ingresada para el plazo fijo es anterior a la fecha actual");
        }
    }
    private void checkEnoughBalance(AccountEntity account, Double amount) throws Exception{
        if(account.getBalance() < amount){
            throw new Exception("No dispone de dinero suficiente en cuenta para realizar el plazo fijo");
        }
    }
}
