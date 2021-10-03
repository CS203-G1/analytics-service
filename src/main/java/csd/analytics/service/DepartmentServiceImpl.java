package csd.analytics.service;

import java.util.List;
import java.util.UUID;

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
    public List<Department> getDepartmentsByCompanyId(UUID companyId) {
        return departmentRepository.findByCompanyId(companyId);
    }
}
