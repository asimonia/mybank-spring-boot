package com.simonian.mybankspringboot.repository;

import com.simonian.mybankspringboot.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, String> {
    Iterable<Transaction> findByReceivingUser(String userId);
}
