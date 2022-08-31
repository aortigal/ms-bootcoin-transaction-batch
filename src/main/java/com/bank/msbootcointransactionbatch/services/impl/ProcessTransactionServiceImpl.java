package com.bank.msbootcointransactionbatch.services.impl;

import com.bank.msbootcointransactionbatch.constants.Constant;
import com.bank.msbootcointransactionbatch.models.documents.Transaction;
import com.bank.msbootcointransactionbatch.models.utils.DataEvent;
import com.bank.msbootcointransactionbatch.services.ProcessTransactionService;
import com.bank.msbootcointransactionbatch.services.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProcessTransactionServiceImpl implements ProcessTransactionService {

    @Autowired
    private TransactionService transactionService;

    private static final Logger log = LoggerFactory.getLogger(ProcessTransactionServiceImpl.class);

    @Override
    public void process(DataEvent<Transaction> dataEvent) {
        log.info("[INI] Process {}", dataEvent.getId());
        switch (dataEvent.getProcess()){
            case Constant.PROCESS_BOOTCOIN_TRANSACTION_CREATE:
                log.info("save Transaction");
                transactionService.create(dataEvent.getData());
                break;
            case Constant.PROCESS_BOOTCOIN_TRANSACTION_UPDATE:
                log.info("update Transaction");
                transactionService.update(dataEvent.getData());
                break;
            case Constant.PROCESS_BOOTCOIN_TRANSFER_YANKI_STATUS:
            case Constant.PROCESS_BOOTCOIN_TRANSFER_BANK_STATUS:
                log.info("update status Transaction process {} ", dataEvent.getProcess());
                transactionService.updateStatus(dataEvent.getData());
                break;
            default:
                log.info("Procces Invalid {}", dataEvent.getProcess());
                break;
        }
        log.info("[END] Process {}", dataEvent.getId());
    }

}
