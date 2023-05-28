package com.royal.reserve.bank.transaction.api.repository;

import com.royal.reserve.bank.transaction.api.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for storing assets in the database.
 * It provides CRUD operations and other database-related functionality.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
