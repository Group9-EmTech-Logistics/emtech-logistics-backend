package com.emtech.logistics.service;

import com.emtech.logistics.dto.InventoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InventoryService {
    Page<InventoryDTO> getAllInventory(Pageable pageable, String search, String category, String status);
    InventoryDTO getInventoryById(Long id);
    InventoryDTO getInventoryBySku(String sku);
    InventoryDTO createInventory(InventoryDTO dto, String token);
    InventoryDTO updateInventory(Long id, InventoryDTO dto, String token);
    void deleteInventory(Long id, String token);
    List<InventoryDTO> getLowStockItems();
    List<InventoryDTO> getExpiringItems(int days);
    InventoryDTO adjustStock(Long id, int quantity, String reason, String token);
}
