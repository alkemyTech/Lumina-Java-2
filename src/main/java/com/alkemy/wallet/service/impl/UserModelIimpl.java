package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.model.UserModel;
import com.alkemy.wallet.dto.requestDto.UserModelRequestDTO;
import com.alkemy.wallet.dto.responseDto.UserModelResponseDTO;
import com.alkemy.wallet.mapping.UserModelMapping;
import com.alkemy.wallet.model.*;
import com.alkemy.wallet.repository.UserModelRepository;
import com.alkemy.wallet.service.service.RoleService;
import com.alkemy.wallet.service.service.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserModelIimpl implements UserModelService {
    @Autowired
    private UserModelRepository userModelRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

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
        return UserModelMapping.convertEntityToDto(userModelRepository.findById(idSender).get());
    }

    public ResponseEntity<UserModelResponseDTO> createUser(UserModelRequestDTO userModelRequestDTO) {

        UserModel newUser = (UserModelMapping.convertDtoToEntity(userModelRequestDTO));

        newUser.setRole(roleService.getRoleByName(userModelRequestDTO.getRole()));

        setAccountToUser(newUser);

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        return ResponseEntity.status(HttpStatus.CREATED).body(UserModelMapping.convertEntityToDTO(userModelRepository.save(newUser)));
    }

    private void setAccountToUser(UserModel user) {
        Account USDAcount = new Account();
        Account ARSAcount = new Account();

        USDAcount.setCurrency(Currency.USD);
        ARSAcount.setCurrency(Currency.ARS);

        user.getAccountsList().add(USDAcount);
        user.getAccountsList().add(ARSAcount);

        USDAcount.setBalance(0d);
        ARSAcount.setBalance(0d);

        ARSAcount.setTransactionLimit(0d);
        USDAcount.setTransactionLimit(0d);

        ARSAcount.setUser(user);
        USDAcount.setUser(user);
    }
}
