package com.mini.shopify.inventory.api.service;

import com.mini.shopify.inventory.api.dto.InventoryResponse;
import com.mini.shopify.inventory.api.model.Inventory;
import com.mini.shopify.inventory.api.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        //Use case of Circuit breaker: slow network connection
        log.info("Wait");
        //Thread.sleep(10000);
        log.info("Wait ended");
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(this::mapInventoryAndThrowErrorIfBadluck)
                .toList();
    }

    private InventoryResponse mapInventoryAndThrowErrorIfBadluck(Inventory inventory) {
        return InventoryResponse.builder()
                .skuCode(inventory.getSkuCode())
                .isInStock(inventory.getQuantity() > 0)
                .build();
    }
}
