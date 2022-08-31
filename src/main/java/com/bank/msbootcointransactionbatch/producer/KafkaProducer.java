package com.bank.msbootcointransactionbatch.producer;

import com.bank.msbootcointransactionbatch.models.utils.DataEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

    @Value("${kafka.topic.yanki}")
    private String topicTransferYanki;

    @Autowired
    private KafkaTemplate<String, DataEvent<?>> producer;

    public void sendMessageTransferYanki(DataEvent<?> dataEvent) {
        log.info("Producing message {}", dataEvent.toString());
        this.producer.send(topicTransferYanki, dataEvent);
    }

}