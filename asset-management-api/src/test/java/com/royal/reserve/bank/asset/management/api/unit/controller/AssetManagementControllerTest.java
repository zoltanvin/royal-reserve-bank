package com.royal.reserve.bank.asset.management.api.unit.controller;

import com.royal.reserve.bank.asset.management.api.controller.AssetManagementController;
import com.royal.reserve.bank.asset.management.api.dto.AssetManagementResponse;
import com.royal.reserve.bank.asset.management.api.service.AssetManagementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link AssetManagementController} class.
 */
@ExtendWith(MockitoExtension.class)
class AssetManagementControllerTest {

    @Mock
    private AssetManagementService assetManagementService;

    /**
     * Test for the {@link AssetManagementController#isAssetAvailable(List)} method.
     */
    @Test
    void testIsAssetAvailableReturnsOk() {
        // Given
        AssetManagementController assetManagementController = new AssetManagementController(assetManagementService);
        List<String> assetCodes = Arrays.asList("8917", "1355");
        List<AssetManagementResponse> expectedResponse = Arrays.asList(
                new AssetManagementResponse("8917", true),
                new AssetManagementResponse("1355", false)
        );
        when(assetManagementService.isAssetAvailable(assetCodes)).thenReturn(expectedResponse);

        // When
        ResponseEntity<List<AssetManagementResponse>> response =
                ResponseEntity.ok(assetManagementController.isAssetAvailable(assetCodes));

        // Then
        verify(assetManagementService, times(1)).isAssetAvailable(assetCodes);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    /**
     * Test for the {@link AssetManagementController#isAssetAvailable(List)} method.
     */
    @Test
    void testIsAssetAvailableWithEmptyAssetCodeList() {
        // Given
        AssetManagementController assetManagementController = new AssetManagementController(assetManagementService);
        List<String> emptyAssetCodes = List.of();

        // When
        List<AssetManagementResponse> response = assetManagementController.isAssetAvailable(emptyAssetCodes);

        // Then
        assertTrue(response.isEmpty());
    }
}
