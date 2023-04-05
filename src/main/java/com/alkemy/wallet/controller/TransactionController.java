package com.alkemy.wallet.controller;

import com.alkemy.wallet.dto.requestDto.TransactionRequestDTO;
import com.alkemy.wallet.dto.responseDto.TransactionResponseDTO;
import com.alkemy.wallet.model.Transaction;
import com.alkemy.wallet.service.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private TransactionService transactionService;

    @PostMapping("/sendUsd/{idSender}")
    public ResponseEntity<?> sendUsd(@PathVariable Long idSender, @RequestBody TransactionRequestDTO transactionRequestDTO) throws Exception {
        try {
            return ResponseEntity.ok(transactionService.sendUsd(transactionRequestDTO, idSender));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getStackTrace());
        }
    }

    @PostMapping("/sendArs/{idSender}")
    public ResponseEntity<?> sendArs(@PathVariable Long idSender, @RequestBody TransactionRequestDTO transactionRequestDTO) throws Exception {
        try {
            return ResponseEntity.ok(transactionService.sendArs(transactionRequestDTO, idSender));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getStackTrace());
        }
    }

    @GetMapping("/transactions/{userId}")
    public ResponseEntity<?> transactionList(@PathVariable long userId) throws Exception {
        try{
            return ResponseEntity.ok(transactionService.trasactionList(userId));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getStackTrace());
        }
    }
}
