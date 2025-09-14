package com.emtech.logistics.service;

import java.util.Map;

public interface AnalyticsService {
    Map<String, Object> getDashboardMetrics();
    Map<String, Object> getSalesAnalytics();
}
