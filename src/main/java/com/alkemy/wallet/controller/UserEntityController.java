package com.alkemy.wallet.controller;

import com.alkemy.wallet.dto.requestDto.UserEntityRequestDTO;
import com.alkemy.wallet.dto.responseDto.UserEntityResponseDTO;
import com.alkemy.wallet.model.UserEntity;
import com.alkemy.wallet.service.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<UserEntity>> getUserList() {
        return userEntityService.getUserList();
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
}
