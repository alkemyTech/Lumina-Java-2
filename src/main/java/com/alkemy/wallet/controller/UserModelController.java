package com.alkemy.wallet.controller;

import com.alkemy.wallet.service.service.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserModelController {
    @Autowired
    private UserModelService userModelService;

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> softDelete(@PathVariable("userId") Long userId) {
        return userModelService.softDelete(userId);
    }
}
