package com.emtech.logistics.repository;

import com.emtech.logistics.model.InventoryItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryItemEntity, Long> {

    /**
     * Search inventory items by name, SKU, or category.
     */
    @Query("SELECT i FROM InventoryItemEntity i WHERE " +
           "LOWER(i.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(i.sku) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(i.category) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<InventoryItemEntity> searchInventory(@Param("query") String query);

    /**
     * Count items where quantity is below a given threshold (low stock alert).
     */
    @Query("SELECT COUNT(i) FROM InventoryItemEntity i WHERE i.quantity < :threshold")
    Long countLowStockItems(@Param("threshold") Integer threshold);

    /**
     * Find all items in a specific category.
     */
    List<InventoryItemEntity> findByCategoryIgnoreCase(String category);

    /**
     * Find an item by its SKU.
     */
    Optional<InventoryItemEntity> findBySkuIgnoreCase(String sku);

    /**
     * Get items that are completely out of stock.
     */
    @Query("SELECT i FROM InventoryItemEntity i WHERE i.quantity = 0")
    List<InventoryItemEntity> findOutOfStockItems();
}
