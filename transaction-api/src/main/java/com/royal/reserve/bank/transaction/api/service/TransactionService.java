package com.royal.reserve.bank.transaction.api.service;

import com.royal.reserve.bank.transaction.api.client.AssetManagementClient;
import com.royal.reserve.bank.transaction.api.dto.AssetManagementResponse;
import com.royal.reserve.bank.transaction.api.dto.TransactionItemsDto;
import com.royal.reserve.bank.transaction.api.dto.TransactionRequest;
import com.royal.reserve.bank.transaction.api.event.TransactionEvent;
import com.royal.reserve.bank.transaction.api.model.Transaction;
import com.royal.reserve.bank.transaction.api.model.TransactionItems;
import com.royal.reserve.bank.transaction.api.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AssetManagementClient assetManagementClient;
    private final KafkaTemplate<String, TransactionEvent> kafkaTemplate;

    public String processTransaction(TransactionRequest transactionRequest) {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(UUID.randomUUID().toString());

        List<TransactionItems> transactionItems = transactionRequest.getTransactionItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        transaction.setTransactionItemsList(transactionItems);

        List<String> assetCodes = transaction.getTransactionItemsList().stream()
                .map(TransactionItems::getAssetCode)
                .toList();

        boolean assetIsAvailable = assetManagementClient.checkAssetAvailability(assetCodes)
                .stream()
                .allMatch(AssetManagementResponse::isAssetAvailable);

        if (assetIsAvailable) {
            transactionRepository.save(transaction);
            kafkaTemplate.send("notificationTopic", new TransactionEvent(transaction.getTransactionId()));
            return "Transaction completed successfully!";
        } else {
            throw new IllegalArgumentException("Asset is not available, please try again later");
        }
    }

    private TransactionItems mapToDto(TransactionItemsDto transactionItemsDto) {
        TransactionItems transactionItems = new TransactionItems();
        transactionItems.setAssetCode(transactionItemsDto.getAssetCode());
        transactionItems.setAssetName(transactionItemsDto.getAssetName());
        transactionItems.setValue(transactionItemsDto.getValue());
        return transactionItems;
    }
}
