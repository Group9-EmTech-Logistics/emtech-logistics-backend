// src/main/java/com/emtech/logistics/controller/InventoryController.java
package com.emtech.logistics.controller;

import com.emtech.logistics.dto.InventoryDTO;
import com.emtech.logistics.service.InventoryService;
import com.emtech.logistics.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class InventoryController {

    private final InventoryService inventoryService;

    /**
     * Fetch all inventory with optional search, category, and status filters.
     */
    @GetMapping
    public ResponseEntity<ResponseUtil.ApiResponse<Page<InventoryDTO>>> getAllInventory(
            Pageable pageable,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String status) {
        try {
            Page<InventoryDTO> inventory = inventoryService.getAllInventory(pageable, search, category, status);
            return ResponseUtil.success(inventory);
        } catch (Exception e) {
            return ResponseUtil.error("Failed to fetch inventory: " + e.getMessage());
        }
    }

    /**
     * Fetch inventory by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseUtil.ApiResponse<InventoryDTO>> getInventoryById(@PathVariable Long id) {
        try {
            InventoryDTO inventory = inventoryService.getInventoryById(id);
            return ResponseUtil.success(inventory);
        } catch (Exception e) {
            return ResponseUtil.error("Inventory item not found: " + e.getMessage());
        }
    }

    /**
     * Fetch inventory by SKU.
     */
    @GetMapping("/sku/{sku}")
    public ResponseEntity<ResponseUtil.ApiResponse<InventoryDTO>> getInventoryBySku(@PathVariable String sku) {
        try {
            InventoryDTO inventory = inventoryService.getInventoryBySku(sku);
            return ResponseUtil.success(inventory);
        } catch (Exception e) {
            return ResponseUtil.error("Inventory item not found: " + e.getMessage());
        }
    }

    /**
     * Create a new inventory item.
     */
    @PostMapping
    public ResponseEntity<ResponseUtil.ApiResponse<InventoryDTO>> createInventory(
            @Valid @RequestBody InventoryDTO inventoryDTO,
            @RequestHeader("Authorization") String token) {
        try {
            InventoryDTO created = inventoryService.createInventory(inventoryDTO, token);
            return ResponseUtil.success("Inventory item created successfully", created);
        } catch (Exception e) {
            return ResponseUtil.error("Failed to create inventory item: " + e.getMessage());
        }
    }

    /**
     * Update an existing inventory item.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResponseUtil.ApiResponse<InventoryDTO>> updateInventory(
            @PathVariable Long id,
            @Valid @RequestBody InventoryDTO inventoryDTO,
            @RequestHeader("Authorization") String token) {
        try {
            InventoryDTO updated = inventoryService.updateInventory(id, inventoryDTO, token);
            return ResponseUtil.success("Inventory item updated successfully", updated);
        } catch (Exception e) {
            return ResponseUtil.error("Failed to update inventory item: " + e.getMessage());
        }
    }

    /**
     * Delete an inventory item.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseUtil.ApiResponse<String>> deleteInventory(
            @PathVariable Long id,
            @RequestHeader("Authorization") String token) {
        try {
            inventoryService.deleteInventory(id, token);
            return ResponseUtil.success("Inventory item deleted successfully", null);
        } catch (Exception e) {
            return ResponseUtil.error("Failed to delete inventory item: " + e.getMessage());
        }
    }

    /**
     * Fetch low stock items.
     */
    @GetMapping("/low-stock")
    public ResponseEntity<ResponseUtil.ApiResponse<List<InventoryDTO>>> getLowStockItems() {
        try {
            List<InventoryDTO> lowStockItems = inventoryService.getLowStockItems();
            return ResponseUtil.success(lowStockItems);
        } catch (Exception e) {
            return ResponseUtil.error("Failed to fetch low stock items: " + e.getMessage());
        }
    }

    /**
     * Fetch items that are expiring within the specified number of days.
     */
    @GetMapping("/expiring")
    public ResponseEntity<ResponseUtil.ApiResponse<List<InventoryDTO>>> getExpiringItems(
            @RequestParam(defaultValue = "30") int days) {
        try {
            List<InventoryDTO> expiringItems = inventoryService.getExpiringItems(days);
            return ResponseUtil.success(expiringItems);
        } catch (Exception e) {
            return ResponseUtil.error("Failed to fetch expiring items: " + e.getMessage());
        }
    }

    /**
     * Adjust stock quantity with a reason.
     */
    @PostMapping("/{id}/adjust-stock")
    public ResponseEntity<ResponseUtil.ApiResponse<InventoryDTO>> adjustStock(
            @PathVariable Long id,
            @RequestParam int quantity,
            @RequestParam String reason,
            @RequestHeader("Authorization") String token) {
        try {
            InventoryDTO adjusted = inventoryService.adjustStock(id, quantity, reason, token);
            return ResponseUtil.success("Stock adjusted successfully", adjusted);
        } catch (Exception e) {
            return ResponseUtil.error("Failed to adjust stock: " + e.getMessage());
        }
    }
}
