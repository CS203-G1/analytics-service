package csd.analytics.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import csd.analytics.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    List<Employee> findByDepartmentId(UUID departmentId);
    Optional<Employee> findByIdAndDepartmentId(UUID employeeId, UUID departmentId);
    List<Employee> findByCreatedAtBetween(Date start, Date end);
}
