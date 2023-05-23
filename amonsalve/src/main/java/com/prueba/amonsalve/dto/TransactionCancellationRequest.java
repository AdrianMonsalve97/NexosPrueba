package com.prueba.amonsalve.dto;

public class TransactionCancellationRequest {
    private String cardId;
    private String transactionId;

    public TransactionCancellationRequest() {
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
