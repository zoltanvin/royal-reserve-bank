package com.royal.reserve.bank.transaction.api.client;

import com.royal.reserve.bank.transaction.api.dto.AssetManagementResponse;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * A Feign client interface for interacting with the Asset Management API.
 */
@FeignClient(name = "asset-management-api")
@Retry(name = "asset-management")
public interface AssetManagementClient {

    /**
     *
     *Retrieves asset availability information from the Asset Management API.
     *@param assetCode The list of asset codes to check availability for.
     *@return A list of AssetManagementResponse objects containing asset availability information.
     */
    @GetMapping("/api/asset-management")
    List<AssetManagementResponse> checkAssetAvailability(@RequestParam List<String> assetCode);
}