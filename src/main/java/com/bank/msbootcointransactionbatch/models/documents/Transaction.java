package com.bank.msbootcointransactionbatch.models.documents;

import com.bank.msbootcointransactionbatch.models.enums.PayMode;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "transactions")
public class Transaction {

    @Id
    private String id;

    private String ticket;

    private String senderAccount;

    private String senderName;

    private String recipientAccount;

    private String recipientName;

    private float rateAmount;

    private float amount;

    private PayMode payMode;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeStamp;

    private String state;
}
