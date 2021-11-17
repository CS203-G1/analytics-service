package csd.analytics.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csd.analytics.model.Department;
import csd.analytics.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<UUID> getDepartmentIdsByCompanyId(UUID companyId) {
        List<Department> departments = departmentRepository.findByCompanyId(companyId);
        List<UUID> departmentIds = departments
                .stream()
                .map(department -> department.getId())
                .collect(Collectors.toList());
        return departmentIds;
    }
}
