package com.example.Banking.service;

import com.example.Banking.dto.AccountResponse;
import com.example.Banking.dto.CreateAccountRequest;
import com.example.Banking.dto.CreateTransactionRequest;
import com.example.Banking.dto.CreateTransactionResponse;
import com.example.Banking.dto.TransactionResponse;
import com.example.Banking.enums.Type;
import com.example.Banking.model.Account;
import com.example.Banking.model.Transaction;
import com.example.Banking.repository.AccountRepository;
import com.example.Banking.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BankingService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public BankingService(AccountRepository accountRepository,
                          TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    // All access to the in-memory storage uses the same service lock.
    public synchronized AccountResponse createAccount(CreateAccountRequest request) {
        BigDecimal balance = request.initialBalance() == null
                ? BigDecimal.ZERO : request.initialBalance();
        Account account = new Account(request.iban(), request.ownerName(), balance);
        return AccountResponse.from(accountRepository.save(account));
    }

    public synchronized AccountResponse getAccount(UUID id) {
        return AccountResponse.from(findAccount(id));
    }

    public synchronized List<TransactionResponse> getTransactions(UUID accountId) {
        findAccount(accountId);
        List<TransactionResponse> result = new ArrayList<>();
        for (Transaction transaction : transactionRepository.findByAccountId(accountId)) {
            result.add(TransactionResponse.from(transaction));
        }
        return result;
    }

    public synchronized CreateTransactionResponse transfer(CreateTransactionRequest request) {
        if (request.amount() == null || request.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Amount must be positive");
        }
        if (request.fromAccountId().equals(request.toAccountId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Cannot transfer to the same account");
        }

        Account sender = findAccount(request.fromAccountId());
        Account receiver = findAccount(request.toAccountId());
        BigDecimal amount = request.amount();
        if (sender.getBalance().compareTo(amount) < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient funds");
        }

        LocalDateTime createdAt = LocalDateTime.now();
        Transaction withdrawal = new Transaction(sender, Type.WITHDRAW, amount,
                createdAt, request.note());
        Transaction deposit = new Transaction(receiver, Type.DEPOSIT, amount,
                createdAt, request.note());

        sender.setBalance(sender.getBalance().subtract(amount));
        receiver.setBalance(receiver.getBalance().add(amount));
        accountRepository.save(sender);
        accountRepository.save(receiver);
        transactionRepository.save(withdrawal);
        transactionRepository.save(deposit);

        return new CreateTransactionResponse(withdrawal.getId(), sender.getId(), receiver.getId(),
                amount, createdAt, request.note());
    }

    private Account findAccount(UUID id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Account not found: " + id));
    }
}
