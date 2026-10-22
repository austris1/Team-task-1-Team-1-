package com.example.Banking.model;


import com.example.Banking.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class User {


    private UUID id;
    private String username;
    private String passwordHash;
    private Role role;

    public User(String username,
                String passwordHash) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = Role.USER;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(passwordHash, user.passwordHash) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, passwordHash, role);
    }
}
