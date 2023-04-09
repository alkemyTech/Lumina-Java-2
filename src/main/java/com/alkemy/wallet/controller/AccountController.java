package com.alkemy.wallet.controller;

import com.alkemy.wallet.dto.AccountDTO;
import com.alkemy.wallet.dto.requestDto.AccountForPostRequestDTO;
import com.alkemy.wallet.dto.responseDto.AccountForPostResponseDTO;
import com.alkemy.wallet.dto.responseDto.BalanceResponseDTO;
import com.alkemy.wallet.service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<AccountDTO>> accountsOfUser(@PathVariable Long userId) {
        return ResponseEntity.ok(accountService.accountsOfUser(userId));
    }

    @GetMapping("/balance/{userId}")
    public ResponseEntity<BalanceResponseDTO> getBalance(@PathVariable Long userId) {
        BalanceResponseDTO balanceResponseDTO = accountService.getBalance(userId);
        return ResponseEntity.status(HttpStatus.OK).body(balanceResponseDTO);
    }

    @PostMapping
    public ResponseEntity<AccountForPostResponseDTO> createUser(@RequestBody AccountForPostRequestDTO accountForPostRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.saveAccount((accountForPostRequestDTO)));
    }
}
