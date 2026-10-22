package com.example.Banking.dto;

import com.example.Banking.enums.Type;
import com.example.Banking.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionResponse(
        UUID id,
        UUID accountId,
        Type type,
        BigDecimal amount,
        LocalDateTime createdAt,
        String note) {

    public static TransactionResponse from(Transaction t) {
        return new TransactionResponse(t.getId(), t.getAccount().getId(), t.getType(),
                t.getAmount(), t.getCreatedAt(), t.getNote());
    }
}
