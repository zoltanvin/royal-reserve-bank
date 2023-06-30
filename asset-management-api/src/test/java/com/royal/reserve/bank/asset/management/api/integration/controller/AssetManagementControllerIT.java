package com.royal.reserve.bank.asset.management.api.integration.controller;

import com.royal.reserve.bank.asset.management.api.controller.AssetManagementController;
import com.royal.reserve.bank.asset.management.api.dto.AssetManagementResponse;
import com.royal.reserve.bank.asset.management.api.service.AssetManagementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Integration tests for the {@link AssetManagementController} class.
 */
@WebMvcTest
class AssetManagementControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssetManagementService assetManagementService;

    /**
     * Test for the {@link AssetManagementController#isAssetAvailable(List)} method.
     *
     * @throws Exception if an exception occurs during the test
     */
    @Test
    void testIsAssetAvailable() throws Exception {
        // Given
        List<String> assetCodes = Arrays.asList("NVDA", "NFLX");
        List<AssetManagementResponse> mockResponse = Arrays.asList(
                new AssetManagementResponse("NVDA", true),
                new AssetManagementResponse("NFLX", false)
        );
        when(assetManagementService.isAssetAvailable(assetCodes)).thenReturn(mockResponse);

        // When and Then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/asset-management")
                        .param("assetCode", "NVDA", "NFLX")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].assetCode").value("NVDA"))
                .andExpect(jsonPath("$[0].assetAvailable").value(true))
                .andExpect(jsonPath("$[1].assetCode").value("NFLX"))
                .andExpect(jsonPath("$[1].assetAvailable").value(false));
    }
}
