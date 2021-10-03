package csd.analytics.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import csd.analytics.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, UUID> {
    List<Department> findByCompanyId(UUID companyId);
}
