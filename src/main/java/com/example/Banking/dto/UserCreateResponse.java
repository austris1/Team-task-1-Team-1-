package com.example.Banking.dto;

import com.example.Banking.model.User;
import com.example.Banking.enums.Role;

import java.util.UUID;

public record UserCreateResponse(UUID id,
                                 String username,
                                 Role role) {

    public static UserCreateResponse from(User u) {
        return new UserCreateResponse(u.getId(), u.getUsername(), u.getRole());
    }
}
