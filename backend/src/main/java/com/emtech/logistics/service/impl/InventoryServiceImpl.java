package com.emtech.logistics.service.impl;

import com.emtech.logistics.dto.InventoryDTO;
import com.emtech.logistics.entity.InventoryItemEntity;
import com.emtech.logistics.repository.InventoryRepository;
import com.emtech.logistics.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public Page<InventoryDTO> getAllInventory(Pageable pageable, String search, String category, String status) {
        Page<InventoryItemEntity> page = inventoryRepository.findAll(pageable); // Add filtering logic as needed
        return page.map(this::toDTO);
    }

    @Override
    public InventoryDTO getInventoryById(Long id) {
        return inventoryRepository.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public InventoryDTO getInventoryBySku(String sku) {
        return inventoryRepository.searchInventory(sku).stream().findFirst().map(this::toDTO).orElse(null);
    }

    @Override
    public InventoryDTO createInventory(InventoryDTO dto, String token) {
        InventoryItemEntity entity = toEntity(dto);
        return toDTO(inventoryRepository.save(entity));
    }

    @Override
    public InventoryDTO updateInventory(Long id, InventoryDTO dto, String token) {
        InventoryItemEntity entity = inventoryRepository.findById(id).orElseThrow();
        entity.setName(dto.getName());
        entity.setSku(dto.getSku());
        entity.setCategory(dto.getCategory());
        entity.setQuantity(dto.getQuantity());
        entity.setStatus(dto.getStatus());
        entity.setOrigin(dto.getOrigin());
        entity.setExpiryDate(dto.getExpiryDate());
        return toDTO(inventoryRepository.save(entity));
    }

    @Override
    public void deleteInventory(Long id, String token) {
        inventoryRepository.deleteById(id);
    }

    @Override
    public List<InventoryDTO> getLowStockItems() {
        return inventoryRepository.findAll().stream()
                .filter(item -> item.getQuantity() < 5) // example threshold
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<InventoryDTO> getExpiringItems(int days) {
        LocalDate targetDate = LocalDate.now().plusDays(days);
        return inventoryRepository.findAll().stream()
                .filter(item -> item.getExpiryDate() != null && !item.getExpiryDate().isAfter(targetDate))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InventoryDTO adjustStock(Long id, int quantity, String reason, String token) {
        InventoryItemEntity entity = inventoryRepository.findById(id).orElseThrow();
        entity.setQuantity(entity.getQuantity() + quantity);
        return toDTO(inventoryRepository.save(entity));
    }

    private InventoryDTO toDTO(InventoryItemEntity entity) {
        return InventoryDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .sku(entity.getSku())
                .category(entity.getCategory())
                .quantity(entity.getQuantity())
                .status(entity.getStatus())
                .origin(entity.getOrigin())
                .expiryDate(entity.getExpiryDate())
                .build();
    }

    private InventoryItemEntity toEntity(InventoryDTO dto) {
        return InventoryItemEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .sku(dto.getSku())
                .category(dto.getCategory())
                .quantity(dto.getQuantity())
                .status(dto.getStatus())
                .origin(dto.getOrigin())
                .expiryDate(dto.getExpiryDate())
                .build();
    }
}
