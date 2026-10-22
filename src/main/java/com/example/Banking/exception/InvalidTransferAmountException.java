package com.example.Banking.exception;

public class InvalidTransferAmountException extends RuntimeException {
    public InvalidTransferAmountException() {
        super("Invalid transfer amount. Amount must be greater than 0.");
    }
}
