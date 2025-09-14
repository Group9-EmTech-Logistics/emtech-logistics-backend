package com.emtech.logistics.service.impl;

import com.emtech.logistics.repository.InventoryRepository;
import com.emtech.logistics.repository.SalesRepository;
import com.emtech.logistics.repository.CustomerRepository;
import com.emtech.logistics.service.AIInsightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AIInsightServiceImpl implements AIInsightService {

    private final SalesRepository salesRepository;
    private final InventoryRepository inventoryRepository;
    private final CustomerRepository customerRepository;

    @Override
    public List<Map<String, Object>> generateInsights() {
        List<Map<String, Object>> insights = new ArrayList<>();

        Map<String, Object> insight1 = new HashMap<>();
        insight1.put("title", "Top Selling Product");
        insight1.put("value", "Product XYZ"); // placeholder, real calculation can use sales data
        insights.add(insight1);

        Map<String, Object> insight2 = new HashMap<>();
        insight2.put("title", "Most Valuable Customer");
        insight2.put("value", "Customer ABC"); // placeholder
        insights.add(insight2);

        Map<String, Object> insight3 = new HashMap<>();
        insight3.put("title", "Low Stock Alerts");
        insight3.put("value", inventoryRepository.countLowStockItems(10)); // threshold 10
        insights.add(insight3);

        return insights;
    }

    @Override
    public Map<String, Object> generateDashboardMetrics() {
        Map<String, Object> metrics = new HashMap<>();
        metrics.put("totalRevenue", salesRepository.getTotalRevenue());
        metrics.put("todaysRevenue", salesRepository.getTodaysRevenue());
        metrics.put("activeCustomersLast30Days", salesRepository.countActiveCustomers());
        metrics.put("lowStockItems", inventoryRepository.countLowStockItems(10));
        metrics.put("totalCustomers", customerRepository.count());
        return metrics;
    }
}
s