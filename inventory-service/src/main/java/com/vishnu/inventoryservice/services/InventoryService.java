package com.vishnu.inventoryservice.services;

import com.vishnu.inventoryservice.DTO.InventoryDTO;
import com.vishnu.inventoryservice.entity.Inventory;
import com.vishnu.inventoryservice.repository.InventoryDAO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static org.hibernate.query.sqm.tree.SqmNode.log;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryDAO repository;

    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryDTO> isInStock(List<Long> productCode) {
        return repository.findByProductCodeIn(productCode)
                .stream()
                .map(inventory -> InventoryDTO
                    .builder()
                    .productCode(inventory.getProductCode())
                    .isInStock(inventory.getQuantity() > 0)
                    .build()
                )
                .toList();
    }
}