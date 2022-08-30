package com.bank.msbootcointransactionbatch.services;

import com.bank.msbootcointransactionbatch.models.documents.Transaction;
import com.bank.msbootcointransactionbatch.models.utils.DataEvent;

public interface ProcessTransactionService {

    void process(DataEvent<Transaction> dataEvent);
}
