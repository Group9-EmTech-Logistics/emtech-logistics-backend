package com.emtech.logistics.controller;

import com.emtech.logistics.service.AnalyticsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/analytics")
@CrossOrigin(origins = "*") // Allow frontend access, you can restrict by domain later
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    /**
     * Fetch dashboard metrics such as revenue, transactions, customers, etc.
     */
    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> getDashboardMetrics() {
        Map<String, Object> metrics = analyticsService.getDashboardMetrics();
        if (metrics == null || metrics.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(metrics);
    }

    /**
     * Fetch detailed sales analytics (total revenue, daily sales trends, etc.)
     */
    @GetMapping("/sales")
    public ResponseEntity<Map<String, Object>> getSalesAnalytics() {
        Map<String, Object> analytics = analyticsService.getSalesAnalytics();
        if (analytics == null || analytics.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(analytics);
    }
}
