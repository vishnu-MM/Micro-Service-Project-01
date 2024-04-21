package com.vishnu.orderservice.configuration;

import com.vishnu.orderservice.DTO.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("INVENTORY-SERVICE")
public interface InventoryInterface {
    @GetMapping("api/inventory")
    List<InventoryResponse> isInStock(@RequestParam List<Long> productCodeList);
}