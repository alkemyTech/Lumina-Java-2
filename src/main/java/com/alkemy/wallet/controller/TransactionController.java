package com.alkemy.wallet.controller;

import com.alkemy.wallet.dto.requestDto.TransactionRequestDTO;
import com.alkemy.wallet.dto.responseDto.TransactionResponseDTO;
import com.alkemy.wallet.service.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/sendUsd/{idSender}")
    public ResponseEntity<?> sendUsd(@PathVariable Long idSender, @RequestBody TransactionRequestDTO transactionRequestDTO) throws Exception {
        try {
            return ResponseEntity.ok(transactionService.sendUsd(transactionRequestDTO, idSender));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getStackTrace());
        }
    }

    @PostMapping("/sendArs/{idSender}")
    public ResponseEntity<?> sendArs(@PathVariable Long idSender, @RequestBody TransactionRequestDTO transactionRequestDTO) throws Exception {
        try {
            return ResponseEntity.ok(transactionService.sendArs(transactionRequestDTO, idSender));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getStackTrace());
        }
    }

    @GetMapping("/transactions/{userId}")
    public ResponseEntity<?> transactionList(@PathVariable Long userId) throws Exception {
        try {
            return ResponseEntity.ok(transactionService.trasactionList(userId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getStackTrace());
        }
    }


    @PostMapping("/transactions/deposit")
    public ResponseEntity<?> makeDeposit(@RequestBody TransactionRequestDTO transactionRequestDTO) throws Exception {
        try {
            return ResponseEntity.ok(transactionService.makeDeposit(transactionRequestDTO));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getStackTrace());
        }
    }

    @PatchMapping("/{transactionId}")
    public void editTransactionDescription(@PathVariable Long transactionId, @RequestBody TransactionRequestDTO transactionRequestDTO) throws Exception {
        transactionService.editTransactionDescription(transactionId, transactionRequestDTO);

    }

    @GetMapping("/transactions-id/{transactionsId}")
    public ResponseEntity<TransactionResponseDTO> trasactionForId(@PathVariable Long transactionsId){
        return transactionService.trasactionForId(transactionsId);


    }

    @PostMapping("/payment")
    public ResponseEntity<TransactionResponseDTO>createTransactionPayment(@RequestBody TransactionRequestDTO transactionRequestDTO){
        return transactionService.createTransactionPayment(transactionRequestDTO);
    }

}
