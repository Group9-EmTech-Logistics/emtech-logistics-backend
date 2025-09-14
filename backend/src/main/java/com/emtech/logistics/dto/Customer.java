package com.emtech.logistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Long id;
    private String name;
    private String businessType;
    private String phone;
    private Integer totalOrders;
    private LocalDateTime lastOrderDate;
}
