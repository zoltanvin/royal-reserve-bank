package com.royal.reserve.bank.asset.management.api.repository;

import com.royal.reserve.bank.asset.management.api.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetManagementRepository extends JpaRepository<Asset, Long> {
    List<Asset> findByAssetCodeIn(List<String> assetCode);
}
