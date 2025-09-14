package com.emtech.logistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryItem {
    private Long id;
    private String name;
    private String sku;
    private String category;
    private Integer quantity;
    private String status;
    private String origin;
    private LocalDate expiryDate;
}
