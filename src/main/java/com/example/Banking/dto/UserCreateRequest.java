package com.example.Banking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserCreateRequest(
        @NotBlank(message = "username is required")
        @Size(min = 3, max = 30, message = "username must be 3-30 characters")
        @Pattern(regexp = "^[a-zA-Z0-9_.-]+$",
                message = "username may contain only letters, digits, '_', '.', '-'")
        String username,
        @NotBlank(message = "password is required")
        @Size(min = 8, max = 72, message = "password must be 8-72 characters")
        String password
) {
}
