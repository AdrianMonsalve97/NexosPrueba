package com.prueba.amonsalve.dto;
public class TransactionRequest {
    private String cardId;
    private double amount;

    // Constructor, getters y setters

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
