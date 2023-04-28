package com.mini.shopify.inventory.api.util;

import com.mini.shopify.inventory.api.model.Inventory;
import com.mini.shopify.inventory.api.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InventoryTestData implements CommandLineRunner {
    private final InventoryRepository inventoryRepository;
    @Override
    public void run(String... args) {
        List<Inventory> inventoryList = new ArrayList<>();

        Inventory inventoryItem1 = new Inventory();
        inventoryItem1.setSkuCode("To Kill a Mockingbird");
        inventoryItem1.setQuantity(15);
        inventoryList.add(inventoryItem1);

        Inventory inventoryItem2 = new Inventory();
        inventoryItem2.setSkuCode("Pride and Prejudice");
        inventoryItem2.setQuantity(3);
        inventoryList.add(inventoryItem2);

        Inventory inventoryItem3 = new Inventory();
        inventoryItem3.setSkuCode("The Great Gatsby");
        inventoryItem3.setQuantity(8);
        inventoryList.add(inventoryItem3);

        Inventory inventoryItem4 = new Inventory();
        inventoryItem4.setSkuCode("One Hundred Years of Solitude");
        inventoryItem4.setQuantity(20);
        inventoryList.add(inventoryItem4);

        Inventory inventoryItem5 = new Inventory();
        inventoryItem5.setSkuCode("The Hunger Games");
        inventoryItem5.setQuantity(0);
        inventoryList.add(inventoryItem5);

        inventoryRepository.saveAll(inventoryList);
    };
}

