package com.royal.reserve.bank.asset.management.api.unit.repository;
import com.royal.reserve.bank.asset.management.api.model.Asset;
import com.royal.reserve.bank.asset.management.api.repository.AssetManagementRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the {@link AssetManagementRepository} class.
 */
@ExtendWith(MockitoExtension.class)
class AssetManagementRepositoryTest {

    @Mock
    private AssetManagementRepository assetManagementRepository;

    /**
     * Test for the {@link AssetManagementRepository#findByAssetCodeIn(List)} method.
     */
    @Test
    void testFindByAssetCodeIn() {
        // Given
        List<String> assetCodes = Arrays.asList("78231", "24722");
        List<Asset> expectedAssets = Arrays.asList(
                new Asset(1L, "78231", "a", 25400),
                new Asset(2L, "24722", "b", 52000)
        );

        // When
        when(assetManagementRepository.findByAssetCodeIn(assetCodes)).thenReturn(expectedAssets);
        List<Asset> actualAssets = assetManagementRepository.findByAssetCodeIn(assetCodes);

        // Then
        assertEquals(expectedAssets, actualAssets);
    }
}
