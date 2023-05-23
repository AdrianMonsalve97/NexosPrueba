package com.prueba.amonsalve.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.amonsalve.Exceptions.CustomExceptions.CardAlreadyActivatedException;
import com.prueba.amonsalve.dto.CardBlockedException;
import com.prueba.amonsalve.dto.InsufficientBalanceException;
import com.prueba.amonsalve.model.Card;
import com.prueba.amonsalve.repository.CardRepository;
import com.prueba.amonsalve.repository.TransactionRepository;

import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;
@Service
public class CardService {
    @Autowired
CardRepository cardRepository;
    @Autowired
    TransactionRepository transactionRepository;

    

    
   

    public Card createCard(String productId, String holderName) {
 
        String cardNumber = generateCardNumber(productId);

    
        LocalDate expirationDate = LocalDate.now().plusYears(3);


        Card card = new Card(cardNumber, holderName, expirationDate, 0.0, false, false);

        cardRepository.save(card);

        return card;
    }

    public void activateCard(String cardId) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Card not found with id: " + cardId));
        card.setActivated(true);
        cardRepository.save(card);
    }

    public void blockCard(String cardId) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Card not found with id: " + cardId));
        card.setBlocked(true);
        cardRepository.save(card);
    }

    public void reloadBalance(String cardId, double amount) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Card not found with id: " + cardId));
        card.setBalance(card.getBalance() + amount);
        cardRepository.save(card);
    }

    public void updateCardBalance(String cardId, double amount) {
        Optional<Card> optionalCard = cardRepository.findById(cardId);
        if (optionalCard.isEmpty()) {
            throw new EntityNotFoundException("Card not found with id: " + cardId);
        }

        Card card = optionalCard.get();

    
        double currentBalance = card.getBalance();
        double newBalance = currentBalance + amount;
        card.setBalance(newBalance);
        cardRepository.save(card);
    }



    public double getCardBalance(String cardId) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Card not found with id: " + cardId));
        return card.getBalance();
    }

    public void makePurchase(String cardId, double price) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Card not found with id: " + cardId));

        if (!card.isActivated()) {
            throw new CardAlreadyActivatedException("Card is not activated: " + cardId);
        }

        if (card.isBlocked()) {
            throw new CardBlockedException("Card is blocked: " + cardId);
        }

        if (card.getBalance() < price) {
            throw new InsufficientBalanceException("Insufficient balance for card: " + cardId);
        }

        
        card.setBalance(card.getBalance() - price);
        cardRepository.save(card);
    }

    public String generateCardNumber(String productId) {
        String cardNumber = productId + generateRandomString(10);

        int remainingDigits = 16 - cardNumber.length();
        cardNumber += generateRandomDigits(remainingDigits);

        return cardNumber;
    }

    private String generateRandomString(int length) {
        String validCharacters = "0123456789";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(validCharacters.length());
            stringBuilder.append(validCharacters.charAt(randomIndex));
        }
        return stringBuilder.toString();
    }

    private String generateRandomDigits(int length) {
        String validDigits = "0123456789";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(validDigits.length());
            stringBuilder.append(validDigits.charAt(randomIndex));
        }
        return stringBuilder.toString();
    }

    public Card getCard(String cardId) {
        Optional<Card> optionalCard = cardRepository.findById(cardId);
        if (optionalCard.isEmpty()) {
            throw new EntityNotFoundException("Card not found with id: " + cardId);
        }
        return optionalCard.get();
    }
    
}
