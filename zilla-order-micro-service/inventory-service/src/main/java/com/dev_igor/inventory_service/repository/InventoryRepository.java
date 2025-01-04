package com.dev_igor.inventory_service.repository;

import com.dev_igor.inventory_service.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<List<Inventory>> findBySkuCode(String skuCode);
}
