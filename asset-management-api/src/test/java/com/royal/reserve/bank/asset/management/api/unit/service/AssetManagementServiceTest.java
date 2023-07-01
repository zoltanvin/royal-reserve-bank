package com.royal.reserve.bank.asset.management.api.unit.service;

import com.royal.reserve.bank.asset.management.api.dto.AssetManagementResponse;
import com.royal.reserve.bank.asset.management.api.model.Asset;
import com.royal.reserve.bank.asset.management.api.repository.AssetManagementRepository;
import com.royal.reserve.bank.asset.management.api.service.AssetManagementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the {@link AssetManagementService} class.
 */
@ExtendWith(MockitoExtension.class)
class AssetManagementServiceTest {

    @Mock
    private AssetManagementRepository assetManagementRepository;

    /**
     * Test for the {@link AssetManagementService#isAssetAvailable(List)} method.
     */
    @Test
    void testIsAssetAvailable() {
        // Given
        AssetManagementService assetManagementService = new AssetManagementService(assetManagementRepository);
        List<String> assetCodes = Arrays.asList("387223", "081293");
        Asset asset1 = new Asset(813L, "387223", "QQQ", 12000);
        Asset asset2 = new Asset(114L, "081293", "KD", 3000);
        List<Asset> assets = Arrays.asList(asset1, asset2);
        when(assetManagementRepository.findByAssetCodeIn(assetCodes)).thenReturn(assets);

        // When
        List<AssetManagementResponse> response = assetManagementService.isAssetAvailable(assetCodes);

        // Then
        assertEquals(2, response.size());
        AssetManagementResponse response1 = response.get(0);
        assertEquals("387223", response1.getAssetCode());
        assertTrue(response1.isAssetAvailable());
        AssetManagementResponse response2 = response.get(1);
        assertEquals("081293", response2.getAssetCode());
        assertTrue(response2.isAssetAvailable());
    }
}
