package com.alkemy.wallet.service.service;

import com.alkemy.wallet.dto.requestDto.UserModelRequestDTO;
import com.alkemy.wallet.dto.responseDto.UserModelResponseDTO;
import com.alkemy.wallet.model.UserModel;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserModelService {
    ResponseEntity<String> softDelete (Long userId);

    ResponseEntity<List<UserModel>> getUserList();

    ResponseEntity<UserModelResponseDTO> createUser (UserModelRequestDTO userModelRequestDTO);

}
