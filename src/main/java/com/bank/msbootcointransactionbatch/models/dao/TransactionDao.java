package com.bank.msbootcointransactionbatch.models.dao;

import com.bank.msbootcointransactionbatch.models.documents.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionDao extends MongoRepository<Transaction, String> {
}
