package com.royal.reserve.bank.transaction.api.repository;

import com.royal.reserve.bank.transaction.api.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
