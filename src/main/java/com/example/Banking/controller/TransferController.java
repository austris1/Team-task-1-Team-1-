package com.example.Banking.controller;

import com.example.Banking.dto.CreateTransactionRequest;
import com.example.Banking.dto.CreateTransactionResponse;
import com.example.Banking.service.BankingService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {

    private final BankingService bankingService;

    public TransferController(BankingService bankingService) {
        this.bankingService = bankingService;
    }

    @PostMapping("/transfer")
    public CreateTransactionResponse transfer(@Valid @RequestBody CreateTransactionRequest request) {
        return bankingService.transfer(request);
    }
}
