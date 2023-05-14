package com.royal.reserve.bank.transaction.api.client;

import com.royal.reserve.bank.transaction.api.dto.AssetManagementResponse;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "asset-management-api")
@Retry(name = "asset-management")
public interface AssetManagementClient {
    @GetMapping("/api/asset-management")
    List<AssetManagementResponse> checkAssetAvailability(@RequestParam List<String> assetCode);
}