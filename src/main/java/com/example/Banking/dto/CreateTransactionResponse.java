package com.example.Banking.dto;

import com.example.Banking.enums.Type;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateTransactionResponse(
        UUID transactionId,
        UUID fromAccountId,
        UUID toAccountId,
        Double amount,
        Type status,
        LocalDateTime createdAt,
        String note
) {
}
