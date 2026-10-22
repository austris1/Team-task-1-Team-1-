package com.example.Banking.service;

import com.example.Banking.exception.AccountNotFoundException;
import com.example.Banking.model.Account;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AccountService {
    private final Map<UUID, Account> accounts = new ConcurrentHashMap<>();

    public Account createAccount(String iban, String ownerName, BigDecimal openingBalance) {
        Account account = new Account(iban, ownerName, openingBalance);
        accounts.put(account.getId(), account);
        return account;
    }

    public Account getAccount(UUID id) {
        Account account = accounts.get(id);
        if (account == null) {
            throw new AccountNotFoundException();
        }
        return account;
    }
}
