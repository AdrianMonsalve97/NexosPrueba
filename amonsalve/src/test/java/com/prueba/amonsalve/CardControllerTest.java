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

import com.prueba.amonsalve.controllers.CardController;
import com.prueba.amonsalve.model.Card;
import com.prueba.amonsalve.service.CardService;

class CardControllerTest {

    @Mock
    private CardService cardService;

    @InjectMocks
    private CardController cardController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void activateCard_ValidCardId_ReturnsSuccessMessage() {
        // Mocking
        String cardId = "cardId";
        doNothing().when(cardService).activateCard(cardId);
    
        // Test
        ResponseEntity<String> response = cardController.activateCard(cardId);
    
        // Verify
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Card activated successfully", response.getBody());
        verify(cardService).activateCard(cardId);
    }
    


    @Test
    void blockCard_ValidCardId_ReturnsSuccessMessage() {
        // Mocking
        String cardId = "cardId";
        doNothing().when(cardService).blockCard(cardId);

        // Test
        ResponseEntity<String> response = cardController.blockCard(cardId);

        // Verify
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Card blocked successfully", response.getBody());
        verify(cardService).blockCard(cardId);
    }

    @Test
    void getCard_ValidCardId_ReturnsCardDetails() {
        // Mocking
        String cardId = "cardId";
        Card expectedCard = new Card();
        when(cardService.getCard(cardId)).thenReturn(expectedCard);
    
        // Test
        ResponseEntity<Card> response = cardController.getCard(cardId);
    
        // Verify
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedCard, response.getBody());
        verify(cardService).getCard(cardId);
    }
    
}
