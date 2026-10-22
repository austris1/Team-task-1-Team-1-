package com.example.Banking.controller;

import com.example.Banking.dto.CreateTransactionRequest;
import com.example.Banking.dto.CreateTransactionResponse;
import com.example.Banking.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/transfer")
public class TransferController {

    private final TransferService transferService;

    @Autowired // I know that for 1 constructor we don't need to use @Autowired, but it's for readability
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public CreateTransactionResponse transfer(@Valid @RequestBody CreateTransactionRequest request) {
        return transferService.transfer(request);
    }

}
