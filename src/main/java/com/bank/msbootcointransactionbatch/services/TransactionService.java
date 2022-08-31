package com.bank.msbootcointransactionbatch.services;

import com.bank.msbootcointransactionbatch.models.documents.Transaction;

import java.util.Optional;

public interface TransactionService {
    Optional<Transaction> create(Transaction transaction);

    Optional<Transaction> update(Transaction transaction);

    Optional<Transaction> updateStatus(Transaction transaction);

}
