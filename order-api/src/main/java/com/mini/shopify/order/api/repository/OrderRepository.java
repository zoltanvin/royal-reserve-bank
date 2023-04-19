package com.mini.shopify.order.api.repository;

import com.mini.shopify.order.api.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
