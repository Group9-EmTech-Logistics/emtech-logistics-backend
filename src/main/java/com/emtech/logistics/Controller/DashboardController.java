// src/main/java/com/emtech/logistics/controller/DashboardController.java
package com.emtech.logistics.controller;

import com.emtech.logistics.service.AIInsightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/dashboard")
@CrossOrigin(origins = "*") // ⚠️ Replace * with frontend domain in production
public class DashboardController {

    private final AIInsightService aiInsightService;

    public DashboardController(AIInsightService aiInsightService) {
        this.aiInsightService = aiInsightService;
    }

    /**
     * Get AI-powered insights for dashboard
     */
    @GetMapping("/insights")
    public ResponseEntity<List<Map<String, Object>>> getAIInsights() {
        List<Map<String, Object>> insights = aiInsightService.generateInsights();

        if (insights == null || insights.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 if no insights available
        }
        return ResponseEntity.ok(insights); // 200 with data
    }

    /**
     * Get overall dashboard metrics
     */
    @GetMapping("/metrics")
    public ResponseEntity<Map<String, Object>> getDashboardMetrics() {
        Map<String, Object> metrics = aiInsightService.generateDashboardMetrics();

        if (metrics == null || metrics.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(metrics);
    }
}
