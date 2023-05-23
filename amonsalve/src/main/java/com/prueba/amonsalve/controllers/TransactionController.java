package com.prueba.amonsalve.controllers;

import com.prueba.amonsalve.dto.TransactionCancellationRequest;
import com.prueba.amonsalve.dto.TransactionRequest;
import com.prueba.amonsalve.model.Transaction;
import com.prueba.amonsalve.service.CardService;
import com.prueba.amonsalve.service.TransactionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/transaction")
@Api(tags = "transaction API")
public class TransactionController {

    @Autowired
     TransactionService transactionService;
     public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @Autowired
     CardService cardService;

     @PostMapping("/create")
     @ApiOperation("create a transaction")
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionRequest request) {
        String cardId = request.getCardId();
        double amount = request.getAmount();

       
        Transaction transaction = transactionService.createTransaction(cardId, amount);

        return ResponseEntity.ok(transaction);
    }

    @PostMapping("/cancel")
    @ApiOperation("Cancel a transaction")
    public ResponseEntity<String> cancelTransaction(@RequestBody TransactionCancellationRequest request) {
        String cardId = request.getCardId();
        String transactionId = request.getTransactionId();

        transactionService.cancelTransaction(cardId, transactionId);

        return ResponseEntity.ok("Transaction canceled successfully");
    }

    @GetMapping("/{transactionId}")
    @ApiOperation("Get transaction details by ID")
    public ResponseEntity<Transaction> getTransaction(@PathVariable String transactionId) {
        Transaction transaction = transactionService.getTransaction(transactionId);

        return ResponseEntity.ok(transaction);
    }
}
