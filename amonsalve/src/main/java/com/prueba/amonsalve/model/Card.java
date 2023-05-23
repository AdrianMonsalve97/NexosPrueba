
package com.prueba.amonsalve.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String cardNumber;
    private String holderName;
    private LocalDate expirationDate;
    private double balance;
    private boolean activated;
    private boolean blocked;

    public Card() {
    }

    public Card(String cardNumber, String holderName, LocalDate expirationDate, double balance, boolean activated, boolean blocked) {
        this.cardNumber = cardNumber;
        this.holderName = holderName;
        this.expirationDate = expirationDate;
        this.balance = balance;
        this.activated = activated;
        this.blocked = blocked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
