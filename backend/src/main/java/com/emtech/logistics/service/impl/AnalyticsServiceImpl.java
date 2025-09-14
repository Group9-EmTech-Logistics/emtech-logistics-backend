package com.emtech.logistics.service.impl;

import com.emtech.logistics.repository.InventoryRepository;
import com.emtech.logistics.repository.SalesRepository;
import com.emtech.logistics.repository.CustomerRepository;
import com.emtech.logistics.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {

    private final SalesRepository salesRepository;
    private final InventoryRepository inventoryRepository;
    private final CustomerRepository customerRepository;

    @Override
    public Map<String, Object> getDashboardMetrics() {
        Map<String, Object> metrics = new HashMap<>();
        metrics.put("totalRevenue", salesRepository.getTotalRevenue());
        metrics.put("todaysRevenue", salesRepository.getTodaysRevenue());
        metrics.put("totalTransactions", salesRepository.countTodaysTransactions());
        metrics.put("activeCustomersLast30Days", salesRepository.countActiveCustomers());
        metrics.put("lowStockItems", inventoryRepository.countLowStockItems(10)); // threshold 10
        metrics.put("totalCustomers", customerRepository.count());
        return metrics;
    }

    @Override
    public Map<String, Object> getSalesAnalytics() {
        Map<String, Object> analytics = new HashMap<>();
        analytics.put("totalRevenue", salesRepository.getTotalRevenue());
        analytics.put("todaysRevenue", salesRepository.getTodaysRevenue());
        analytics.put("totalTransactions", salesRepository.countTodaysTransactions());
        analytics.put("activeCustomers", salesRepository.countActiveCustomers());
        return analytics;
    }
}
