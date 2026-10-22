package com.example.Banking.controller;


import com.example.Banking.dto.AccountResponse;
import com.example.Banking.dto.CreateAccountRequest;
import com.example.Banking.dto.TransactionResponse;
import com.example.Banking.service.AccountService;
import com.example.Banking.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final TransferService transferService;

    @Autowired // I know that for 1 constructor we don't need to use @Autowired, but it's for readability
    public AccountController(AccountService accountService, TransferService transferService) {
        this.accountService = accountService;
        this.transferService = transferService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse createAccount(@Valid @RequestBody CreateAccountRequest request) {
        BigDecimal initialBalance = request.initialBalance() != null ? request.initialBalance() : BigDecimal.ZERO;
        return AccountResponse.from(accountService.createAccount(
                request.iban(),
                request.ownerName(),
                initialBalance));
    }

    @GetMapping("/{id}")
    public AccountResponse getAccount(@PathVariable UUID id) {
        return AccountResponse.from(accountService.getAccount(id));
    }

    @GetMapping("/{id}/transactions")
    public List<TransactionResponse> getTransactions(@PathVariable UUID id) {
        return transferService.getTransactions(id).stream()
                .map(TransactionResponse::from)
                .toList();
    }


}
