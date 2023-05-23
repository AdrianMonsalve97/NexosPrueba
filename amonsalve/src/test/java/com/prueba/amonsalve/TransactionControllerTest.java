package com.prueba.amonsalve;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.prueba.amonsalve.controllers.TransactionController;
import com.prueba.amonsalve.dto.TransactionCancellationRequest;
import com.prueba.amonsalve.dto.TransactionRequest;
import com.prueba.amonsalve.model.Transaction;
import com.prueba.amonsalve.service.CardService;
import com.prueba.amonsalve.service.TransactionService;

class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @Mock
    private CardService cardService;

    @InjectMocks
    private TransactionController transactionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTransaction_ValidRequest_ReturnsTransaction() {
        // Mocking
        TransactionRequest request = new TransactionRequest();
        request.setCardId("cardId");
        request.setAmount(100.0);

        Transaction transaction = new Transaction();
        when(transactionService.createTransaction("cardId", 100.0)).thenReturn(transaction);

        // Test
        ResponseEntity<Transaction> response = transactionController.createTransaction(request);

        // Verify
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transaction, response.getBody());
        verify(transactionService).createTransaction("cardId", 100.0);
    }

    @Test
    void cancelTransaction_ValidRequest_ReturnsSuccessMessage() {
        // Mocking
        TransactionCancellationRequest request = new TransactionCancellationRequest();
        request.setCardId("cardId");
        request.setTransactionId("transactionId");

        // Test
        ResponseEntity<String> response = transactionController.cancelTransaction(request);

        // Verify
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Transaction canceled successfully", response.getBody());
        verify(transactionService).cancelTransaction("cardId", "transactionId");
    }

    @Test
    void getTransaction_ValidTransactionId_ReturnsTransaction() {
        // Mocking
        String transactionId = "transactionId";
        Transaction transaction = new Transaction();
        when(transactionService.getTransaction(transactionId)).thenReturn(transaction);

        // Test
        ResponseEntity<Transaction> response = transactionController.getTransaction(transactionId);

        // Verify
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transaction, response.getBody());
        verify(transactionService).getTransaction(transactionId);
    }
}
