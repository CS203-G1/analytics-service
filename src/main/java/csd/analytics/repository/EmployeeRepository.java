package csd.analytics.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import csd.analytics.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    List<Employee> findByDepartmentId(UUID departmentId);
    
    @Query("select e from Employee e where e.department.company.id = :id")
    List<Employee> findAllByCompanyId(@Param("id") UUID companyId);

    Optional<Employee> findByIdAndDepartmentId(UUID employeeId, UUID departmentId);
    List<Employee> findByCreatedAtBetweenAndDepartmentIdIn(Date start, Date end, List<UUID> departmentIds);
    List<Employee> findByDepartmentIdIn(List<UUID> departmentIds);
}
