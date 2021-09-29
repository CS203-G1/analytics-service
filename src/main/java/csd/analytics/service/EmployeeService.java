package csd.analytics.service;

import csd.analytics.model.Employee;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    Employee addEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(UUID id);

    Employee updateEmployeeById(UUID id, Employee employee);

    void deleteEmployeeById(UUID id);
}
