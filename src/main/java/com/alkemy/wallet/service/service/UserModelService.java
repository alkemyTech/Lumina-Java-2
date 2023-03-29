package com.alkemy.wallet.service.service;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;

public interface UserModelService {
    ResponseEntity<String> softDelete (Long userId);
}
