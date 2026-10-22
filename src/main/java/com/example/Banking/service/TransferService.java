package com.example.Banking.service;

import com.example.Banking.dto.CreateTransactionRequest;
import com.example.Banking.dto.CreateTransactionResponse;
import com.example.Banking.enums.Type;
import com.example.Banking.exception.InsufficientFundsException;
import com.example.Banking.exception.InvalidTransferAmountException;
import com.example.Banking.exception.SameAccountTransferException;
import com.example.Banking.model.Account;
import com.example.Banking.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.math.BigDecimal;
import java.util.Comparator;

@Service
public class TransferService {
    private final AccountService accountService;
    private final Map<UUID, Transaction> transactions = new ConcurrentHashMap<>();

    public TransferService(AccountService accountService) {
        this.accountService = accountService;
    }

    public synchronized CreateTransactionResponse transfer(CreateTransactionRequest request) {
        Account from = accountService.getAccount(request.fromAccountId());
        Account to = accountService.getAccount(request.toAccountId());

        if (from.getId().equals(to.getId())) {
            throw new SameAccountTransferException();
        }
        if (request.amount() == null || request.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidTransferAmountException();
        }
        BigDecimal amount = request.amount();
        if (from.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException();
        }

        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));

        LocalDateTime createdAt = LocalDateTime.now();
        Transaction debit = new Transaction(from, Type.WITHDRAW,
                request.amount(), createdAt, request.note());
        Transaction credit = new Transaction(to, Type.DEPOSIT,
                request.amount(), createdAt, request.note());
        transactions.put(debit.getId(), debit);
        transactions.put(credit.getId(), credit);

        return new CreateTransactionResponse(debit.getId(), from.getId(), to.getId(),
                request.amount(), createdAt, request.note());
    }

    public List<Transaction> getTransactions(UUID accountId) {
        accountService.getAccount(accountId);
        return transactions.values().stream()
                .filter(transaction -> transaction.getAccount().getId().equals(accountId))
                .sorted(Comparator.comparing(Transaction::getCreatedAt))
                .toList();
    }
}
