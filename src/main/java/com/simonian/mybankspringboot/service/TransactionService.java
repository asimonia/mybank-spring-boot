package com.simonian.mybankspringboot.service;

import com.simonian.mybankspringboot.model.Transaction;
import com.simonian.mybankspringboot.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class TransactionService {

    private final String bankSlogan;

    private final TransactionRepository transactionRepository;

    public TransactionService(@Value("${bank.slogan}") String bankSlogan, TransactionRepository transactionRepository) {
        this.bankSlogan = bankSlogan;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public Iterable<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Transactional
    public Iterable<Transaction> findByReceivingUserId(String userId) {
        return transactionRepository.findByReceivingUser(userId);
    }

    public Transaction create(BigDecimal amount, String reference, String receivingUser) {
        ZonedDateTime timestamp = ZonedDateTime.now();
        Transaction transaction = new Transaction(amount, timestamp, reference, bankSlogan, receivingUser);

        transaction = transactionRepository.save(transaction);
        return transaction;
    }
}
