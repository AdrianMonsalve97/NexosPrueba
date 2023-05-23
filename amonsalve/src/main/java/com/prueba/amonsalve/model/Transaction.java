package com.prueba.amonsalve.model;

import java.time.LocalDateTime;


import jakarta.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Card card;

    private LocalDateTime timestamp;

    private double amount;

    private boolean canceled;

    public Transaction() {
    }

    public Transaction(Card card, LocalDateTime timestamp, double amount) {
        this.card = card;
        this.timestamp = timestamp;
        this.amount = amount;
        this.canceled = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public LocalDateTime getCreationDate() {
        return timestamp;
    }
}
