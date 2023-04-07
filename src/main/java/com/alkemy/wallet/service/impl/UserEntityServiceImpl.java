package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.model.UserEntity;
import com.alkemy.wallet.dto.requestDto.UserEntityRequestDTO;
import com.alkemy.wallet.dto.responseDto.UserEntityResponseDTO;
import com.alkemy.wallet.mapping.UserModelMapping;
import com.alkemy.wallet.model.AccountEntity;
import com.alkemy.wallet.model.Currency;
import com.alkemy.wallet.repository.UserModelRepository;
import com.alkemy.wallet.service.service.RoleService;
import com.alkemy.wallet.service.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEntityServiceImpl implements UserEntityService {
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
    public ResponseEntity<List<UserEntity>> getUserList() {
        return ResponseEntity.status(HttpStatus.OK).body(userModelRepository.findAll());
    }

    @Override
    public UserEntityResponseDTO getUserById(Long idSender) {
        return UserModelMapping.convertEntityToDTO(userModelRepository.findById(idSender).get());
    }

    public ResponseEntity<UserEntityResponseDTO> createUser(UserEntityRequestDTO userEntityRequestDTO) {

        UserEntity newUser = (UserModelMapping.convertDtoToEntity(userEntityRequestDTO));

        newUser.setRole(roleService.getRoleByName(userEntityRequestDTO.getRole()));

        setAccountToUser(newUser);

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        return ResponseEntity.status(HttpStatus.CREATED).body(UserModelMapping.convertEntityToDTO(userModelRepository.save(newUser)));
    }

    @Override
    public UserEntity getUserEntityById(Long userId) {
        return userModelRepository.findById(userId).get();
    }

    private void setAccountToUser(UserEntity user) {
        AccountEntity USDAcount = new AccountEntity();
        AccountEntity ARSAcount = new AccountEntity();

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
