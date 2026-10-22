package com.example.Banking.repository;

import com.example.Banking.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class TransactionRepository {

    private final List<Transaction> transactions = new ArrayList<>();

    public Transaction save(Transaction transaction) {
        transactions.add(transaction);
        return transaction;
    }

    public List<Transaction> findByAccountId(UUID accountId) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAccount().getId().equals(accountId)) {
                result.add(transaction);
            }
        }
        return result;
    }
}
