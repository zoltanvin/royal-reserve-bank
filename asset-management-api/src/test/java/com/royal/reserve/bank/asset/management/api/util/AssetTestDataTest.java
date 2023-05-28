package com.royal.reserve.bank.asset.management.api.util;

import com.royal.reserve.bank.asset.management.api.model.Asset;
import com.royal.reserve.bank.asset.management.api.repository.AssetManagementRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Unit tests for the {@link AssetTestData} class.
 */
class AssetTestDataTest {

    @Mock
    private AssetManagementRepository assetManagementRepository;

    /**
     * Test for the {@link AssetTestData#run(String...)} method.
     */
    @Test
    void testPopulatingAssetDataIntoRepository() {
        // Given
        MockitoAnnotations.openMocks(this);
        AssetTestData assetTestData = new AssetTestData(assetManagementRepository);

        List<Asset> assetList = new ArrayList<>();
        Asset assetItem1 = new Asset();
        assetItem1.setAssetCode("SEC");
        assetItem1.setAssetName("Corporate bonds");
        assetItem1.setValue(250000);
        assetList.add(assetItem1);

        Asset assetItem2 = new Asset();
        assetItem2.setAssetCode("BTC");
        assetItem2.setAssetName("Bitcoin");
        assetItem2.setValue(1000000);
        assetList.add(assetItem2);

        Asset assetItem3 = new Asset();
        assetItem3.setAssetCode("INV");
        assetItem3.setAssetName("Stocks");
        assetItem3.setValue(750000);
        assetList.add(assetItem3);

        Asset assetItem4 = new Asset();
        assetItem4.setAssetCode("DERIV");
        assetItem4.setAssetName("Options contracts");
        assetItem4.setValue(0);
        assetList.add(assetItem4);

        Asset assetItem5 = new Asset();
        assetItem5.setAssetCode("LEASE");
        assetItem5.setAssetName("Lease agreements");
        assetItem5.setValue(150000);
        assetList.add(assetItem5);

        // When
        assetTestData.run();

        // Then
        verify(assetManagementRepository, times(1)).saveAll(assetList);

        // Verify the saved assets in the repository. the data is populated when the application
        // starts and not during the test execution. Therefore, for testing purposes, I manually
        // save the test data into the repository within the test method.
        @SuppressWarnings("unchecked")
        ArgumentCaptor<List<Asset>> captor = ArgumentCaptor.forClass(List.class);
        verify(assetManagementRepository).saveAll(captor.capture());
        List<Asset> savedAssets = captor.getValue();

        assertEquals(assetList.size(), savedAssets.size());

        for (int i = 0; i < assetList.size(); i++) {
            Asset expectedAsset = assetList.get(i);
            Asset savedAsset = savedAssets.get(i);
            assertEquals(expectedAsset.getAssetCode(), savedAsset.getAssetCode());
            assertEquals(expectedAsset.getAssetName(), savedAsset.getAssetName());
            assertEquals(expectedAsset.getValue(), savedAsset.getValue());
        }
    }
}