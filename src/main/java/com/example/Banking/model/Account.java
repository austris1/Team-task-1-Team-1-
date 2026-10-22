package com.example.Banking.model;


import lombok.Data;


import java.math.BigDecimal;
import java.util.UUID;

@Data
public class Account {


    private final UUID id;
    private String iban;
    private String ownerName;
    private BigDecimal balance;

    public Account(String iban,
                   String ownerName,
                   BigDecimal balance) {
        this.id = UUID.randomUUID();
        this.iban = iban;
        this.ownerName = ownerName;
        this.balance = balance;
    }


}
