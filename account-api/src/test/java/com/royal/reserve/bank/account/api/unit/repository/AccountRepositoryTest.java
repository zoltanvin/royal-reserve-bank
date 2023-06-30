package com.royal.reserve.bank.account.api.unit.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.royal.reserve.bank.account.api.controller.AccountController;
import com.royal.reserve.bank.account.api.dto.AccountRequest;
import com.royal.reserve.bank.account.api.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;

import java.math.BigDecimal;
import java.util.Currency;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountRepositoryTest {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.5");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccountRepository accountRepository;

    static {
        mongoDBContainer.start();
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    /**
     * Test for the {@link AccountController#createAccount(AccountRequest)} method.
     *
     * @throws Exception if an exception occurs during the test
     */
    @Test
    void shouldCreateAccount() throws Exception {
        // Given
        AccountRequest accountRequest = getAccountRequest();
        String accountRequestString = objectMapper.writeValueAsString(accountRequest);
        int oldSize = accountRepository.findAll().size();

        // When
        mockMvc.perform(MockMvcRequestBuilders.post("/api/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(accountRequestString))
                .andExpect(status().isCreated());

        // Then
        Assertions.assertEquals(oldSize + 1, accountRepository.findAll().size());
    }

    /**
     * Helper method to create an {@link AccountRequest} object.
     *
     * @return the created {@link AccountRequest} object
     */
    private AccountRequest getAccountRequest() {
        return AccountRequest.builder()
                .accountHolderName("James Smith")
                .balance(BigDecimal.valueOf(2199))
                .currency(Currency.getInstance("EUR"))
                .build();
    }
}
