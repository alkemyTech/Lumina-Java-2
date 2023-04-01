package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.model.RoleName;
import com.alkemy.wallet.model.UserModel;
import com.alkemy.wallet.repository.UserModelRepository;
import com.alkemy.wallet.service.service.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EnumType;
import java.util.List;

@Service
public class UserModelIimpl implements UserModelService {
    @Autowired
    private UserModelRepository userModelRepository;

    @Override
    public ResponseEntity<String> softDelete(Long userId) {
        userModelRepository.deleteById(userId);
        return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado exitosamente");
    }

    @Override
    public ResponseEntity<List<UserModel>> getUserList() {
        return ResponseEntity.status(HttpStatus.OK).body(userModelRepository.findAll());
    }

    @Override
    public UserModelDTO getUserById(Long idSender) {
        EnumType.valueOf(RoleName.class, "ADMIN");
        RoleName.ADMIN.toString();
        RoleName.
        return UserModelMapping.convertEntityToDto(userModelRepository.findById(idSender).get());
    }
}
