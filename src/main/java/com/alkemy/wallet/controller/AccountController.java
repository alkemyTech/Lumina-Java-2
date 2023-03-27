package com.alkemy.wallet.controller;

import com.alkemy.wallet.entity.Account;
import com.alkemy.wallet.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountServiceImpl accountServiceImpl;

    @GetMapping("/{userId}")
    public List<Account> accountsOfUser(@PathVariable Long userId){
        //TODO hacer que se verifique que el usuario es un administrador.
        authorizeUser();
        return accountServiceImpl.accountsOfUser(userId);
    }

    //TODO Completar para que verifique que el usuario es un administrador
    private boolean authorizeUser() {
        return true;
    }
}
