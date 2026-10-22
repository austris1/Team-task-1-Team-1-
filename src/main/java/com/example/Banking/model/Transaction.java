package com.example.Banking.model;

import com.example.Banking.enums.Type;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
public class Transaction {

    private UUID id;
    private Account account;
    private Type type;
    private Double amount;
    private LocalDateTime createdAt;
    private String note;


    public Transaction(Account account,
                       Type type,
                       Double amount,
                       LocalDateTime createdAt,
                       String note) {
        this.id = UUID.randomUUID();
        this.account = account;
        this.type = type;
        this.amount = amount;
        this.createdAt = createdAt;
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) && Objects.equals(account, that.account) && type == that.type && Objects.equals(amount, that.amount) && Objects.equals(createdAt, that.createdAt) && Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account, type, amount, createdAt, note);
    }
}
