package com.alkemy.wallet.controller;

import com.alkemy.wallet.dto.requestDto.TransactionRequestDTO;
import com.alkemy.wallet.dto.responseDto.TransactionResponseDTO;
import com.alkemy.wallet.service.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private TransactionService transactionService;

    @PostMapping("/sendUsd/{idSender}")
    public ResponseEntity<TransactionResponseDTO> sendUsd(@PathVariable Long idSender, @RequestBody TransactionRequestDTO transactionRequestDTO){
        transactionService.sendUsd(transactionRequestDTO, idSender);
        return null;
    }

    @PatchMapping("/{transactionId}")
    public void editTransactionDescription(@PathVariable Long transactionId, @RequestBody TransactionRequestDTO transactionRequestDTO) throws Exception {
        transactionService.editTransactionDescription(transactionId, transactionRequestDTO);
    }
}
