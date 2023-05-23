package com.prueba.amonsalve.Exceptions;

public class CustomExceptions {

    public static class TransactionAlreadyCanceledException extends RuntimeException {
        public TransactionAlreadyCanceledException(String message) {
            super(message);
        }
    }

    public static class TransactionCancellationExpiredException extends RuntimeException {
        private String cardId;
        private String transactionId;

        public TransactionCancellationExpiredException(String cardId, String transactionId) {
            super("Transaction cancellation expired for cardId: " + cardId + ", transactionId: " + transactionId);
            this.cardId = cardId;
            this.transactionId = transactionId;
        }

        public String getCardId() {
            return cardId;
        }

        public String getTransactionId() {
            return transactionId;
        }
    }

    public static class CardAlreadyActivatedException extends RuntimeException {
        public CardAlreadyActivatedException(String message) {
            super(message);
        }
    }

}
