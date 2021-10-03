package csd.analytics.service;

import java.util.UUID;

public interface StatisticsService {
    void insertSnapshot(UUID companyId);
    double employeeTurnoverRate();
    double employeeSickRate();
}
