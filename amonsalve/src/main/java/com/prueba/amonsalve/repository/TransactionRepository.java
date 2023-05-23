package com.prueba.amonsalve.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.amonsalve.model.Transaction;



public interface TransactionRepository extends JpaRepository<Transaction, String> {
    Optional<Transaction> findById(String transactionId);
}
