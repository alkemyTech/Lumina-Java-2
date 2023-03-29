package com.alkemy.wallet.controller;

import com.alkemy.wallet.dto.AccountDTO;
import com.alkemy.wallet.service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountServiceImpl;

    @GetMapping("/{userId}")
    public ResponseEntity<List<AccountDTO>> accountsOfUser(@PathVariable Long userId){
        return ResponseEntity.ok(accountServiceImpl.accountsOfUser(userId));
    }

}
