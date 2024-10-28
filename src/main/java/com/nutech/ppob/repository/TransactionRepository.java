package com.nutech.ppob.repository;



import com.nutech.ppob.entity.Transaction;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TransactionRepository extends JpaRepository<Transaction,Long> {


    List<Transaction> findByCustomerEmailOrderByCreatedOnDesc(String email);

    List<Transaction> findByCustomerEmailOrderByCreatedOnDesc(String email, PageRequest pageRequest);
}

