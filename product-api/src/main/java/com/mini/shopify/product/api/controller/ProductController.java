package com.mini.shopify.product.api.controller;

import com.mini.shopify.product.api.service.ProductService;
import com.mini.shopify.product.api.dto.ProductResponse;
import com.mini.shopify.product.api.dto.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product created successfully: \"" +
                productRequest.getName() + "\"");
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct(@RequestBody ProductRequest productRequest) {
        try {
            productService.deleteProductByName(productRequest.getName());
            return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully: \"" +
                    productRequest.getName() + "\"");
        } catch (RuntimeException runtimeException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(runtimeException.getMessage());
        }
    }
}
