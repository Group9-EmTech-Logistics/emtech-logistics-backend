package com.emtech.logistics.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class SalesDTO {
    
    private Long id;
    private String transactionId;

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    private String customerName;

    @NotNull(message = "Sales items are required")
    private List<SalesItemDTO> salesItems;

    private BigDecimal totalAmount;
    private BigDecimal profit;

    @NotNull(message = "Sale type is required")
    private String saleType;

    private String paymentMethod;
    private String status;
    private String notes;
    private LocalDateTime saleDate;

    @Data
    @Builder
    public static class SalesItemDTO {
        private Long id;

        @NotNull(message = "Inventory ID is required")
        private Long inventoryId;

        private String itemName;

        @NotNull(message = "Quantity is required")
        @Positive(message = "Quantity must be positive")
        private Integer quantity;

        @NotNull(message = "Unit price is required")
        @Positive(message = "Unit price must be positive")
        private BigDecimal unitPrice;

        private BigDecimal totalPrice;
        private BigDecimal profit;
    }
}
