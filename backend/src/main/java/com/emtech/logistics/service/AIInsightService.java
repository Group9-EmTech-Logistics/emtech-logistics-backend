package com.emtech.logistics.service;

import java.util.List;
import java.util.Map;

public interface AIInsightService {
    List<Map<String, Object>> generateInsights();
    Map<String, Object> generateDashboardMetrics();
}
