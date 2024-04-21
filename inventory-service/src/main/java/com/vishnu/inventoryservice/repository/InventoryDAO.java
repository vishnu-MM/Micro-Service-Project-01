package com.vishnu.inventoryservice.repository;

import com.vishnu.inventoryservice.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryDAO extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByProductCode(Long productCode);
    Boolean existsByProductCode(Long productCode);
    List<Inventory> findByProductCodeIn(List<Long> productCode);
}