package com.prueba.amonsalve.service;

import com.prueba.amonsalve.Exceptions.CustomExceptions;
import com.prueba.amonsalve.Exceptions.CustomExceptions.CardAlreadyActivatedException;
import com.prueba.amonsalve.dto.CardBlockedException;
import com.prueba.amonsalve.dto.InsufficientBalanceException;
import com.prueba.amonsalve.model.Card;
import com.prueba.amonsalve.model.Transaction;
import com.prueba.amonsalve.repository.CardRepository;
import com.prueba.amonsalve.repository.TransactionRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.temporal.*;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired TransactionRepository transactionRepository;
    @Autowired CardRepository cardRepository;

   
    public Transaction createTransaction(String cardId, double amount) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Card not found with id: " + cardId));
    
        if (!card.isActivated()) {
            throw new CardAlreadyActivatedException("Card is not activated: " + cardId);
        }
    
        if (card.isBlocked()) {
            throw new CardBlockedException("Card is blocked: " + cardId);
        }
    
        if (card.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance for card: " + cardId);
        }
    
        Transaction transaction = new Transaction();
        transaction.setCard(card);
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setCanceled(false);
    
        double updatedBalance = card.getBalance() - amount;
        card.setBalance(updatedBalance);
        cardRepository.save(card);
    
        return transactionRepository.save(transaction);
    }
    
    

    public void cancelTransaction(String cardId, String transactionId) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(transactionId);
        if (optionalTransaction.isEmpty()) {
            throw new EntityNotFoundException("Transaction not found with id: " + transactionId);
        }

        Transaction transaction = optionalTransaction.get();

        LocalDateTime transactionTime = transaction.getTimestamp();
        LocalDateTime currentTime = LocalDateTime.now();
        long hoursDifference = ChronoUnit.HOURS.between(transactionTime, currentTime);
        if (hoursDifference > 24) {
            throw new CustomExceptions.TransactionCancellationExpiredException(cardId, transactionId);
        }

        transaction.setCanceled(true);
        transactionRepository.save(transaction);
    }


    public Transaction getTransaction(String transactionId) {
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found with id: " + transactionId));
    }
}
