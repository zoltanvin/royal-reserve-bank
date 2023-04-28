package com.mini.shopify.product.api.util;

import com.mini.shopify.product.api.model.Product;
import com.mini.shopify.product.api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductTestData implements CommandLineRunner {
    private final ProductRepository productRepository;
    @Override
    public void run(String... args) {
        List<Product> productList = new ArrayList<>();

        Product productItem1 = new Product();
        productItem1.setId("644d735424862f0104ae52f6");
        productItem1.setName("The Alchemist");
        productItem1.setDescription("Paper book");
        productItem1.setPrice(BigDecimal.valueOf(75));
        productList.add(productItem1);

        Product productItem2 = new Product();
        productItem2.setId("644d736d24862f0104ae52f7");
        productItem2.setName("The Shawshank Redemption");
        productItem2.setDescription("Paper book");
        productItem2.setPrice(BigDecimal.valueOf(59));
        productList.add(productItem2);

        Product productItem3 = new Product();
        productItem3.setId("644d737824862f0104ae52f8");
        productItem3.setName("The Da Vinci Code");
        productItem3.setDescription("Paper book");
        productItem3.setPrice(BigDecimal.valueOf(99));
        productList.add(productItem3);

        productRepository.saveAll(productList);
    };
}

