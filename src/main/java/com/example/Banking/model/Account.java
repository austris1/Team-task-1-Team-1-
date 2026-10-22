package com.example.Banking.model;


import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class Account {


    private UUID id;
    private String iban;
    private String ownerName;
    private Double balance;

    public Account(String iban,
                   String ownerName,
                   Double balance) {
        this.id = UUID.randomUUID();
        this.iban = iban;
        this.ownerName = ownerName;
        this.balance = balance;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(iban, account.iban) && Objects.equals(ownerName, account.ownerName) && Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, iban, ownerName, balance);
    }
}
