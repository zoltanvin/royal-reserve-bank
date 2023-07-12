package com.royal.reserve.bank.notification.api;

import com.royal.reserve.bank.notification.api.event.TransactionEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * Main class for the Notification Api.
 */
@SpringBootApplication
@Slf4j
public class NotificationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationApiApplication.class, args);
	}

	/**
	 *This method is a Kafka message listener for the "notificationTopic" topic.
	 *It handles incoming messages and processes the TransactionEvent object.
	 *@param transactionEvent The TransactionEvent object received from the Kafka message.
	 */
	@KafkaListener(topics = "notificationTopic")
	public void handleNotification(TransactionEvent transactionEvent) {
		log.info("Received notification for transaction: {}", transactionEvent.getTransactionId());
	}
}
