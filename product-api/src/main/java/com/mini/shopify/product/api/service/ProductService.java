package com.mini.shopify.product.api.service;

import com.mini.shopify.product.api.model.Product;
import com.mini.shopify.product.api.dto.ProductResponse;
import com.mini.shopify.product.api.dto.ProductRequest;
import com.mini.shopify.product.api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public void deleteProductByName(String productName) {
        List<Product> products = productRepository.findAll();

        Optional<Product> productToDelete = Optional.empty();
        for (Product p : products) {
            if (p.getName() != null && p.getName().equals(productName)) {
                productToDelete = Optional.of(p);
                break;
            }
        }

        if (productToDelete.isPresent()) {
            productRepository.delete(productToDelete.get());
        } else {
            throw new RuntimeException("Product with name \"" + productName + "\" not found.");
        }
    }
}
