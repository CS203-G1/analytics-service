package csd.analytics.service;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {
    List<UUID> getDepartmentIdsByCompanyId(UUID companyId);
}
