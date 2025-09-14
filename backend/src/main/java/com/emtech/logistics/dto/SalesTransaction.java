package com.emtech.logistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesTransaction {
    private Long id;
    private String transactionId;
    private Long customerId;
    private String customerName;
    private String items; // Could be JSON or Stringified list
    private BigDecimal totalAmount;
    private String transactionType;
    private LocalDateTime transactionDate;
}
