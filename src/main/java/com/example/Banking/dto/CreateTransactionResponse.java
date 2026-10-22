package com.example.Banking.dto;


import java.time.LocalDateTime;
import java.util.UUID;

public record CreateTransactionResponse(
        UUID transactionId,
        UUID fromAccountId,
        UUID toAccountId,
        Double amount,
        LocalDateTime createdAt,
        String note
) {
}
