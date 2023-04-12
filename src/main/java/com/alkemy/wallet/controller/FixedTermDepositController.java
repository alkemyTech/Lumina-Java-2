package com.alkemy.wallet.controller;

import com.alkemy.wallet.dto.requestDto.FixedTermDepositRequestDTO;
import com.alkemy.wallet.service.service.FixedTermDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fixedDeposit")
public class FixedTermDepositController {

    @Autowired
    FixedTermDepositService fixedTermDepositService;
    @PostMapping("/{accountId}/{userId}")
    public ResponseEntity<?> createFixedDeposit(@PathVariable Long accountId, @PathVariable Long userId, @RequestBody FixedTermDepositRequestDTO fixedTermDepositRequestDTO){
        try{
            return fixedTermDepositService.createFixedDeposit(accountId, userId, fixedTermDepositRequestDTO);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getStackTrace());
        }
    }

    @PostMapping("simulate")
    public ResponseEntity<?> simulateFixedDeposit(@RequestBody FixedTermDepositRequestDTO fixedTermDepositRequestDTO){
        try{
            return ResponseEntity.ok(fixedTermDepositService.simulate(fixedTermDepositRequestDTO));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getStackTrace());
        }
    }
}
