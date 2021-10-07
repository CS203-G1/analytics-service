package csd.analytics.service;

import java.util.UUID;

public interface StatisticsService {
    double employeeTurnoverRate(UUID companyId);
    double employeeSickRate();
}
