package com.bank.msbootcointransactionbatch.services.impl;

import com.bank.msbootcointransactionbatch.constants.Constant;
import com.bank.msbootcointransactionbatch.models.documents.Transaction;
import com.bank.msbootcointransactionbatch.models.enums.PayMode;
import com.bank.msbootcointransactionbatch.models.utils.DataEvent;
import com.bank.msbootcointransactionbatch.producer.KafkaProducer;
import com.bank.msbootcointransactionbatch.services.ProcessTransactionService;
import com.bank.msbootcointransactionbatch.services.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class ProcessTransactionServiceImpl implements ProcessTransactionService {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private KafkaProducer kafkaProducer;
    private static final Logger log = LoggerFactory.getLogger(ProcessTransactionServiceImpl.class);

    @Override
    public void process(DataEvent<Transaction> dataEvent) {
        log.info("[INI] Process {}", dataEvent.getId());
        switch (dataEvent.getProcess()){
            case Constant.PROCESS_BOOTCOIN_TRANSACTION_CREATE:
                log.info("save Transaction");
                Optional<Transaction> transaction= transactionService.create(dataEvent.getData());
                if(transaction.isPresent()){
                    dataEvent.setDateEvent(LocalDateTime.now());
                    dataEvent.setData(transaction.get());
                    if(transaction.get().getPayMode().equals(PayMode.YANKI)){
                        dataEvent.setProcess(Constant.PROCESS_TRANSFER_YANKI);
                        kafkaProducer.sendMessageTransferYanki(dataEvent);
                    }else if(transaction.get().getPayMode().equals(PayMode.TRANSFER)){
                        dataEvent.setProcess(Constant.PROCESS_TRANSFER_BANK);
                        dataEvent.setData(transaction.get());
                    }


                }
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
