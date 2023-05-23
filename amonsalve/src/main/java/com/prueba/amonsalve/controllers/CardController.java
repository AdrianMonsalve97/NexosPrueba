package com.prueba.amonsalve.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.prueba.amonsalve.dto.CardBalanceRequest;
import com.prueba.amonsalve.dto.CardCreationRequest;
import com.prueba.amonsalve.dto.PurchaseRequest;
import com.prueba.amonsalve.model.Card;
import com.prueba.amonsalve.service.CardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/card")
@Api(tags = "Card API")
public class CardController {
  

    @Autowired
    CardService cardService;

    @PostMapping("/create")
public ResponseEntity<String> createCard(@RequestBody CardCreationRequest request) {

    String productId = request.getProductId();
    String cardHolderName = request.getCardHolderName();

 
    Card card = cardService.createCard(productId, cardHolderName);
    

    return ResponseEntity.ok("Card created successfully. Card ID: " + card.getId());
}

   

    @GetMapping("/{productId}/number")
    public ResponseEntity<String> generateCardNumber(@PathVariable String productId) {
        String cardNumber = cardService.generateCardNumber(productId);
        return ResponseEntity.ok(cardNumber);
    }

    @PostMapping("/activate")
    @ApiOperation("Activate a card")
    public ResponseEntity<String> activateCard(@RequestBody String cardId) {
        cardService.activateCard(cardId);
        return ResponseEntity.ok("Card activated successfully");
    }
    
    

    @DeleteMapping("/{cardId}")
    public ResponseEntity<String> blockCard(@PathVariable String cardId) {
        cardService.blockCard(cardId);
        return ResponseEntity.ok("Card blocked successfully");
    }

    @PostMapping("/balance")
    public ResponseEntity<String> rechargeBalance(@RequestBody CardBalanceRequest request) {
        String cardId = request.getCardId();
        double balance = request.getBalance();
        cardService.reloadBalance(cardId, balance);
        return ResponseEntity.ok("Balance recharged successfully");
    }

    @GetMapping("/balance/{cardId}")
    public ResponseEntity<Double> getBalance(@PathVariable String cardId) {
        double balance = cardService.getCardBalance(cardId);
        return ResponseEntity.ok(balance);
    }

    @PostMapping("/transaction/purchase")
    public ResponseEntity<String> makePurchase(@RequestBody PurchaseRequest request) {
        String cardId = request.getCardId();
        double price = request.getPrice();
        cardService.makePurchase(cardId, price);
        return ResponseEntity.ok("Purchase successful");
    }

    @GetMapping("/{cardId}")
@ApiOperation("Get card details by ID")
public ResponseEntity<Card> getCard(@PathVariable String cardId) {
    Card card = cardService.getCard(cardId);
    return ResponseEntity.ok(card);
}


  
}
