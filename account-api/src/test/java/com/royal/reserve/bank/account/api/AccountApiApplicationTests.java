package com.royal.reserve.bank.account.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.royal.reserve.bank.account.api.repository.AccountRepository;
import com.royal.reserve.bank.account.api.dto.AccountRequest;
import com.royal.reserve.bank.account.api.service.AccountService;
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
class AccountApiApplicationTests {

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
    static void setProperties(DynamicPropertyRegistry dymDynamicPropertyRegistry) {
        dymDynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    void shouldCreateAccount() throws Exception {
        AccountRequest accountRequest = getAccountRequest();
        String accountRequestString = objectMapper.writeValueAsString(accountRequest);
        int oldSize = accountRepository.findAll().size();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(accountRequestString))
                .andExpect(status().isCreated());
        Assertions.assertEquals(oldSize + 1, accountRepository.findAll().size());
    }

    private AccountRequest getAccountRequest() {
        return AccountRequest.builder()
                .accountHolderName("James Smith")
                .balance(BigDecimal.valueOf(2199))
                .currency(Currency.getInstance("EUR"))
                .build();
    }
}
