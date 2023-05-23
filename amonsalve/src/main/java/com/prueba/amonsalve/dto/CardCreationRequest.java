package com.prueba.amonsalve.dto;


public class CardCreationRequest {

    private String productId;
    private String cardHolderName;



    public CardCreationRequest() {
    }
    public CardCreationRequest(String productId, String cardHolderName) {
        this.productId = productId;
        this.cardHolderName = cardHolderName;
    }
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public String getCardHolderName() {
        return cardHolderName;
    }
    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }
    
}
