package com.royal.reserve.bank.notification.api;

import com.royal.reserve.bank.notification.api.event.TransactionEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class NotificationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationApiApplication.class, args);
	}

	@KafkaListener(topics = "notificationTopic")
	public void handleNotification(TransactionEvent transactionEvent) {
		log.info("Received notification for transaction: {}", transactionEvent.getTransactionId());
	}
}
