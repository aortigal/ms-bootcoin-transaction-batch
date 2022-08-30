package com.bank.msbootcointransactionbatch.services.impl;

import com.bank.msbootcointransactionbatch.constants.Constant;
import com.bank.msbootcointransactionbatch.models.dao.TransactionDao;
import com.bank.msbootcointransactionbatch.models.documents.Transaction;
import com.bank.msbootcointransactionbatch.models.enums.StatusEnum;
import com.bank.msbootcointransactionbatch.models.utils.Status;
import com.bank.msbootcointransactionbatch.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionDao dao;

    @Override
    public Optional<Transaction> create(Transaction transaction) {
        transaction.setStatus(new Status(StatusEnum.CREATE.getCode(), StatusEnum.CREATE.getDescription()));
        return Optional.of(dao.save(transaction));
    }

    @Override
    public Optional<Transaction> update(Transaction transaction) {
        return Optional.of(dao.save(transaction));
    }

}
