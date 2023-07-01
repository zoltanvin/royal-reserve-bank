package com.royal.reserve.bank.account.api.integration.controller;

import com.royal.reserve.bank.account.api.controller.AccountController;
import com.royal.reserve.bank.account.api.service.AccountService;
import com.royal.reserve.bank.account.api.dto.AccountResponse;
import com.royal.reserve.bank.account.api.dto.AccountRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Integration tests for the {@link AccountController} class.
 */
@WebMvcTest
class AccountControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    /**
     * Test for the {@link AccountController#createAccount(AccountRequest)} method.
     *
     * @throws Exception if an exception occurs during the test
     */
    @Test
    void testCreateAccount() throws Exception {
        // Given
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setAccountHolderName("George Clooney");

        // When and Then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"accountHolderName\":\"George Clooney\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content()
                        .string("Successfully set up a new bank account for George Clooney."));
    }

    /**
     * Test for the {@link com.royal.reserve.bank.account.api.controller.AccountController#getAllAccounts()} method.
     *
     * @throws Exception if an exception occurs during the test
     */
    @Test
    void testGetAllAccounts() throws Exception {
        // Given
        List<AccountResponse> mockResponse = Arrays.asList(
                AccountResponse.builder()
                        .id("14312415")
                        .accountNumber("FR32-8472-4321-3894-2384")
                        .accountHolderName("Emma Stone")
                        .balance(BigDecimal.valueOf(283000))
                        .currency(Currency.getInstance("USD"))
                        .build(),
                AccountResponse.builder()
                        .id("94137274")
                        .accountNumber("BG32-8472-4491-3894-2941")
                        .accountHolderName("Jennifer Lawrence")
                        .balance(BigDecimal.valueOf(11400))
                        .currency(Currency.getInstance("EUR"))
                        .build()
        );
        when(accountService.getAllAccounts()).thenReturn(mockResponse);

        // When and Then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/account")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].id").value("14312415"))
                .andExpect(jsonPath("$[0].accountNumber").value("FR32-8472-4321-3894-2384"))
                .andExpect(jsonPath("$[0].accountHolderName").value("Emma Stone"))
                .andExpect(jsonPath("$[0].balance").value(283000))
                .andExpect(jsonPath("$[0].currency").value("USD"))
                .andExpect(jsonPath("$[1].id").value("94137274"))
                .andExpect(jsonPath("$[1].accountNumber").value("BG32-8472-4491-3894-2941"))
                .andExpect(jsonPath("$[1].accountHolderName").value("Jennifer Lawrence"))
                .andExpect(jsonPath("$[1].balance").value(11400))
                .andExpect(jsonPath("$[1].currency").value("EUR"));
    }

    /**
     * Test for the {@link AccountController#deleteAccount(AccountRequest)} method.
     *
     * @throws Exception if an exception occurs during the test
     */
    @Test
    void testDeleteAccount() throws Exception {
        // Given
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setAccountHolderName("Nicole Kidman");

        // When and Then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"accountHolderName\":\"Nicole Kidman\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string("Successfully deleted Nicole Kidman's account."));
    }
}
