package com.alkemy.wallet.controller;

import com.alkemy.wallet.dto.requestDto.UserModelRequestDTO;
import com.alkemy.wallet.dto.responseDto.UserModelResponseDTO;
import com.alkemy.wallet.model.UserModel;
import com.alkemy.wallet.service.service.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserModelController {
    @Autowired
    private UserModelService userModelService;

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> softDelete(@PathVariable("userId") Long userId) {
        return userModelService.softDelete(userId);
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getUserList() {
        return userModelService.getUserList();
    }

    @PostMapping("auth/register")
    public ResponseEntity<UserModelResponseDTO> createUser(
            @Valid @RequestBody UserModelRequestDTO userModelRequestDTO) {
        return userModelService.createUser(userModelRequestDTO);
    }
}
