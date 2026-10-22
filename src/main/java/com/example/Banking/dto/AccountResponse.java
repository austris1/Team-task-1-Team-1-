package com.example.Banking.dto;

import com.example.Banking.model.Account;

import java.util.UUID;

public record AccountResponse(UUID id,
                              String iban,
                              String ownerName,
                              Double balance) {
    public static AccountResponse from(Account a) {
        return new AccountResponse(a.getId(), a.getIban(), a.getOwnerName(), a.getBalance());
    }
}
