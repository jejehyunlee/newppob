package com.nutech.ppob.service;


import com.nutech.ppob.model.request.AuthRequest;
import org.springframework.http.ResponseEntity;

public interface AuthValidation {
    ResponseEntity<Object> validateRegisterRequest(AuthRequest authRequest);

}
