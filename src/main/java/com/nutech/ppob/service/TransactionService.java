package com.nutech.ppob.service;

import com.nutech.ppob.model.request.TopUpRequest;
import com.nutech.ppob.model.request.TransactionProcessRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface TransactionService {

    ResponseEntity<Object> topUpBalance(String token, TopUpRequest request);

    ResponseEntity<Object> ProcessTransaction(String token, TransactionProcessRequest request);

    Map<String , Object> getTransactionHistory(String token, Integer limit);

}
