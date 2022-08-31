package com.bank.msbootcointransactionbatch;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
		"spring.kafka.bootstrap-servers=127.0.0.1:9092",
		"kafka.topic.transaction=bootcoin-transaction-topic",
		"spring.kafka.consumer.group-id=bootcoin-transaction_id",
		"kafka.bootstrapAddress=127.0.0.1:9092",
		"kafka.topic.yanki=yanki"
})
class MsBootcoinTransactionBatchApplicationTests {

	@Test
	void contextLoads() {
	}

}
