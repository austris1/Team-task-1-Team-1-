package com.example.Banking.dto;

import com.example.Banking.model.Account;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountResponse(UUID id,
                              String iban,
                              String ownerName,
                              BigDecimal balance) {
    public static AccountResponse from(Account a) {
        return new AccountResponse(a.getId(), a.getIban(), a.getOwnerName(), a.getBalance());
    }
}
