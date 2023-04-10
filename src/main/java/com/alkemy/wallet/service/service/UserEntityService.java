package com.alkemy.wallet.service.service;

import com.alkemy.wallet.Exception.UserNotFoundException;
import com.alkemy.wallet.dto.requestDto.UserEntityRequestDTO;
import com.alkemy.wallet.dto.responseDto.UserEntityResponseDTO;
import com.alkemy.wallet.model.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserEntityService {
    ResponseEntity<String> softDelete (Long userId);

    ResponseEntity<List<UserEntityResponseDTO>> getUserList();
    UserEntityResponseDTO getUserById(Long userId);
    ResponseEntity<UserEntityResponseDTO> createUser (UserEntityRequestDTO userEntityRequestDTO);
    UserEntity getUserEntityById(Long userId);

    UserEntityResponseDTO updateUser (Long userId, UserEntityRequestDTO dto) throws UserNotFoundException;
}
