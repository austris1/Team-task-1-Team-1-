package com.example.Banking.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CreateTransactionRequest(
        @NotNull(message = "fromAccountId is required")
        UUID fromAccountId,

        @NotNull(message = "toAccountId is required")
        UUID toAccountId,

        @NotNull(message = "amount is required")
        @Positive(message = "amount must be greater than 0")
        Double amount,

        @Size(max = 255, message = "note must be at most 255 characters")
        String note
) {
}
