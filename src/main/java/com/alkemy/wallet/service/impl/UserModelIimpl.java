package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.repository.UserModelRepository;
import com.alkemy.wallet.service.service.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserModelIimpl implements UserModelService {
    @Autowired
    private UserModelRepository userModelRepository;

    @Override
    public ResponseEntity<String> softDelete(Long userId) {
        userModelRepository.deleteById(userId);
        return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado exitosamente");
    }
}
