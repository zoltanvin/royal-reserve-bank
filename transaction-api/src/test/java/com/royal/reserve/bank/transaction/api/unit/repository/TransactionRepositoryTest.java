package com.royal.reserve.bank.transaction.api.unit.repository;

import com.royal.reserve.bank.transaction.api.model.Transaction;
import com.royal.reserve.bank.transaction.api.model.TransactionItems;
import com.royal.reserve.bank.transaction.api.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;


/**
 * Unit tests for the {@link TransactionRepository} class.
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    private Transaction newTransaction;

    @BeforeEach
    void setUp() {
        newTransaction = new Transaction();
        newTransaction.setTransactionId("3532");

        List<TransactionItems> transactionItemsList = new ArrayList<>();
        TransactionItems transactionItem1 = new TransactionItems();
        transactionItemsList.add(transactionItem1);

        TransactionItems transactionItem2 = new TransactionItems();
        transactionItemsList.add(transactionItem2);

        newTransaction.setTransactionItemsList(transactionItemsList);
    }

    /**
     * Test CRUD operations - save.
     */
    @Test
    void testSave() {
        // Given
        Transaction savedTransaction = transactionRepository.save(newTransaction);

        // When and Then
        Assertions.assertNotNull(savedTransaction.getTransactionId());
        Assertions.assertEquals("3532", savedTransaction.getTransactionId());
    }

    /**
     * Test CRUD operations - find by id.
     */
    @Test
    void testFindById() {
        // Given
        Transaction savedTransaction = transactionRepository.save(newTransaction);
        Long transactionId = savedTransaction.getId();

        // When
        Transaction foundTransaction = transactionRepository.findById(transactionId).orElse(null);

        // Then
        Assertions.assertNotNull(foundTransaction);
    }

    /**
     * Test CRUD operations - delete.
     */
    @Test
    void testDelete() {
        // Given
        Transaction savedTransaction = transactionRepository.save(newTransaction);
        Long transactionId = savedTransaction.getId();
        Transaction retrievedTransaction = transactionRepository.findById(transactionId).orElse(null);
        Assertions.assertNotNull(retrievedTransaction);

        // When
        transactionRepository.delete(retrievedTransaction);

        // Then
        Transaction deletedTransaction = transactionRepository.findById(transactionId).orElse(null);
        Assertions.assertNull(deletedTransaction);
    }
}