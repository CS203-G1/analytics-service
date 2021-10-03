package csd.analytics.service;

import java.util.List;
import java.util.UUID;

import csd.analytics.model.Employee;

public interface EmployeeService {
    List<Employee> getAllEmployeesByDepartmentId(UUID departmentId);
    Employee getEmployeeByDepartmentId(UUID departmentId, UUID employeeId);
    Employee getEmployeeById(UUID employeeId);
}
