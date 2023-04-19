package com.mini.shopify.product.api.repository;

import com.mini.shopify.product.api.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
