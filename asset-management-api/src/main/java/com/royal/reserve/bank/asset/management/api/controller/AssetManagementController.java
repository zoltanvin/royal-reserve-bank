package com.royal.reserve.bank.asset.management.api.controller;

import com.royal.reserve.bank.asset.management.api.dto.AssetManagementResponse;
import com.royal.reserve.bank.asset.management.api.service.AssetManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class that handles HTTP requests related to asset management.
 */
@RestController
@RequestMapping("/api/asset-management")
@RequiredArgsConstructor
@Slf4j
public class AssetManagementController {

    private final AssetManagementService assetManagementService;

    /**
     *
     *Retrieves the availability status of assets based on their codes.
     *@param assetCode The list of asset codes to check availability for.
     *@return The list of asset management responses containing the availability status for each asset code.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AssetManagementResponse> isAssetAvailable(@RequestParam List<String> assetCode) {
        log.info("Received asset availability check request for asset code: {}", assetCode);
        return assetManagementService.isAssetAvailable(assetCode);
    }
}

