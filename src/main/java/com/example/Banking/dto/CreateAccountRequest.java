package com.example.Banking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record CreateAccountRequest(

        @NotBlank(message = "Name is required")
        String ownerName,

        @NotBlank
        String iban,

        @PositiveOrZero(message = "initialBalance must not be negative")
        BigDecimal initialBalance

) {
}
