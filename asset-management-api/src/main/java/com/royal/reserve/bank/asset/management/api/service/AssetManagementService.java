package com.royal.reserve.bank.asset.management.api.service;

import com.royal.reserve.bank.asset.management.api.dto.AssetManagementResponse;
import com.royal.reserve.bank.asset.management.api.repository.AssetManagementRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.apache.logging.log4j.ThreadContext.peek;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssetManagementService {

    private final AssetManagementRepository assetManagementRepository;

    @Transactional(readOnly = true)
    @SneakyThrows
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
