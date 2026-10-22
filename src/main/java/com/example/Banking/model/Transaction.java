package com.example.Banking.model;

import com.example.Banking.enums.Type;
import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Transaction {

    private final UUID id;
    private Account account;
    private Type type;
    private BigDecimal amount;
    private LocalDateTime createdAt;
    private String note;


    public Transaction(Account account,
                       Type type,
                       BigDecimal amount,
                       LocalDateTime createdAt,
                       String note) {
        this.id = UUID.randomUUID();
        this.account = account;
        this.type = type;
        this.amount = amount;
        this.createdAt = createdAt;
        this.note = note;
    }

}
