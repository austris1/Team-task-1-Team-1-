package com.example.Banking.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateAccountRequest(

        @NotBlank(message = "Name is required")
        String ownerName,

        @NotBlank
        String iban

) {
}
