package com.prueba.amonsalve.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.amonsalve.model.Card;



public interface CardRepository extends JpaRepository<Card, String> {
    Optional<Card> findById(String cardId);
}
