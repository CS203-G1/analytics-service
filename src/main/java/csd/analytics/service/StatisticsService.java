package csd.analytics.service;

import java.util.UUID;

public interface StatisticsService {
    double getEmployeeTurnoverRate(UUID companyId);
    double getEmployeeSickRate();
}
