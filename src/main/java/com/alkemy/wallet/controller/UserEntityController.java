package com.alkemy.wallet.controller;

import com.alkemy.wallet.Exception.UserNotFoundException;
import com.alkemy.wallet.dto.requestDto.UserEntityRequestDTO;
import com.alkemy.wallet.dto.responseDto.UserEntityResponseDTO;
import com.alkemy.wallet.dto.responseDto.UserEntityTransactionsDTO;
import com.alkemy.wallet.model.UserEntity;
import com.alkemy.wallet.service.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserEntityController {
    @Autowired
    private UserEntityService userEntityService;

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> softDelete(@PathVariable("userId") Long userId) {
        return userEntityService.softDelete(userId);
    }

    @GetMapping
    public ResponseEntity<List<UserEntityResponseDTO>> getUserList() {
        return userEntityService.getUserList();
    }

    @GetMapping("/paged")
    ResponseEntity<?> getUserList(Pageable pageable) throws Exception {
        try{
            return  ResponseEntity.status(HttpStatus.OK).body(userEntityService.getUserList(pageable));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("auth/register")
    public ResponseEntity<UserEntityResponseDTO> createUser(
            @Valid @RequestBody UserEntityRequestDTO userEntityRequestDTO) {
        return userEntityService.createUser(userEntityRequestDTO);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable ("userId") Long userId, @RequestBody UserEntityRequestDTO dto){
        UserEntityResponseDTO updatedUserDTOO = null;
        try{
            updatedUserDTOO = userEntityService.updateUser(userId, dto);
        }catch (UserNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(updatedUserDTOO);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserEntityResponseDTO> getUserById(@PathVariable ("userId") Long userId) {

        return ResponseEntity.status(HttpStatus.OK).body(userEntityService.getUserById(userId));
    }

    @GetMapping("/users-transactions")
    public ResponseEntity<List<UserEntityTransactionsDTO>> getTransactions4User(){
        return ResponseEntity.status(HttpStatus.OK).body(userEntityService.getTransactions4Users());
    }

    //Con paginado
    @GetMapping("/users-transactions/paged")
    public ResponseEntity<?> getTransactions4UserPaged(@PageableDefault(size = 10) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(userEntityService.getTransactions4Users(pageable));
    }

}
