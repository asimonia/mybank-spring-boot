package com.simonian.mybankspringboot.controller;

import com.simonian.mybankspringboot.dto.TransactionDTO;
import com.simonian.mybankspringboot.model.Transaction;
import com.simonian.mybankspringboot.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public Iterable<Transaction> transactions() {
        return transactionService.findAll();
    }

    @GetMapping("/transactions/{userId}")
    public Iterable<Transaction> transactions(@PathVariable String userId) {
        return transactionService.findByReceivingUserId(userId);
    }

    @PostMapping("/transactions")
    public Transaction create(@RequestBody @Valid TransactionDTO transactionDTO) {
        return transactionService.create(transactionDTO.getAmount(), transactionDTO.getReference(), transactionDTO.getReceivingUser());
    }
}
