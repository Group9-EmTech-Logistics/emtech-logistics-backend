package com.emtech.logistics.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Builder
public class BusinessAnalytics {
    private BigDecimal totalRevenue;
    private BigDecimal totalProfit;
    private Integer totalTransactions;
    private Integer totalCustomers;
    private Integer totalProducts;
    private Integer lowStockAlerts;
    private BigDecimal profitMargin;
    private Map<String, BigDecimal> revenueByCategory;
    private Map<String, Integer> salesByCustomerType;
    private Map<String, BigDecimal> profitByMonth;
    private String topSellingProduct;
    private String topCustomer;
    private String trendDirection;
}
