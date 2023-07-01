package com.royal.reserve.bank.asset.management.api.service;

import com.royal.reserve.bank.asset.management.api.dto.AssetManagementResponse;
import com.royal.reserve.bank.asset.management.api.repository.AssetManagementRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class that provides operations for managing assets.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AssetManagementService {

    private final AssetManagementRepository assetManagementRepository;

    /**
     *
     *Checks the availability of assets based on their codes.
     *@param assetCode The list of asset codes to check.
     *@return A list of AssetManagementResponse objects indicating the availability of each asset.
     */
    @Transactional(readOnly = true)
    @SneakyThrows
    @Cacheable("assetAvailability")
    public List<AssetManagementResponse> isAssetAvailable(List<String> assetCode) {
        log.info("Checking asset availability");
        return assetManagementRepository.findByAssetCodeIn(assetCode).stream()
                .map(asset ->
                        AssetManagementResponse.builder()
                                .assetCode(asset.getAssetCode())
                                .isAssetAvailable(asset.getValue() > 0)
                                .build()
                ).toList();
    }
}
