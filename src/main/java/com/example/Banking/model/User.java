package com.example.Banking.model;


import com.example.Banking.enums.Role;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
public class User {


    private final UUID id;
    private String username;

    @ToString.Exclude // to avoid logging the passwordHash
    private String passwordHash;
    private Role role;

    public User(String username,
                String passwordHash) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = Role.USER;
    }

}
