package csd.analytics.service;

import java.util.List;
import java.util.UUID;

import csd.analytics.model.Department;

public interface DepartmentService {
    List<UUID> getDepartmentIdsByCompanyId(UUID companyId);
}
