package com.prueba.amonsalve.dto;

public class PurchaseRequest {
    private String cardId;
    private Double price;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
