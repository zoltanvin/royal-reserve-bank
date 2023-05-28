package com.royal.reserve.bank.asset.management.api.util;

import com.royal.reserve.bank.asset.management.api.repository.AssetManagementRepository;
import com.royal.reserve.bank.asset.management.api.model.Asset;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AssetTestData implements CommandLineRunner {
    private final AssetManagementRepository assetManagementRepository;
    @Override
    public void run(String... args) {
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

        assetManagementRepository.saveAll(assetList);
    }
}

