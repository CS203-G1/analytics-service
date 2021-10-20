package csd.analytics.repository;

import java.time.LocalDateTime;
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

    @Query("select e from Employee e where e.department.company.id = :id and e.createdAt >= :start and e.createdAt <= :end")
    List<Employee> findByCompanyIdAndCreatedAtBetween(@Param("id") UUID companyId, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    List<Employee> findByDepartmentIdIn(List<UUID> departmentIds);
}
