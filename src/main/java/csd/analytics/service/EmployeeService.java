package csd.analytics.service;

import java.util.List;
import java.util.UUID;

import csd.analytics.model.Employee;

public interface EmployeeService {
    List<Employee> getAllEmployeesByDepartmentId(UUID departmentId);
    List<Employee> getAllEmployeesByDepartmentIds(List<UUID> departmentIds);
    List<Employee> getAllEmployeesByCompanyId(UUID companyId);
    Employee getEmployeeByDepartmentId(UUID departmentId, UUID employeeId);
    Employee getEmployeeById(UUID employeeId);
    List<Employee> getEmployeesByCurrentMonth(UUID companyId);
    List<Employee> getEmployeesByTwoWeeks(UUID companyId);
}
