package com.vishnu.inventoryservice.controller;

import com.vishnu.inventoryservice.DTO.InventoryDTO;
import com.vishnu.inventoryservice.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryDTO> isInStock(@RequestParam List<Long> productCodeList) {
        return service.isInStock(productCodeList);
    }
}