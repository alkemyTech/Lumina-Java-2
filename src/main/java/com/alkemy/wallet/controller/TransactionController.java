package com.alkemy.wallet.controller;

import com.alkemy.wallet.dto.requestDto.TransactionDTORequest;
import com.alkemy.wallet.service.service.TransactionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private TransactionService transactionService;

    @PostMapping("/sendUsd/{idSender}")
    public void fun(@PathVariable Long idSender, @RequestBody TransactionDTORequest transactionRequestDTO){
        transactionService.sendUsd(transactionRequestDTO, idSender);
    }
}
