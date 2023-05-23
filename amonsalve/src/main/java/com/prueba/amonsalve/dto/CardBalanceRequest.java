package com.prueba.amonsalve.dto;

public class CardBalanceRequest {
    private String cardId;
    private Double balance;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
