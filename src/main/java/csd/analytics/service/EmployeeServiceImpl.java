package csd.analytics.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csd.analytics.exception.EmployeeNotFoundException;
import csd.analytics.model.Employee;
import csd.analytics.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Method is created for EmployeeVaccination service
    @Override
    public Employee getEmployeeById(UUID employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException(employeeId));
    }

    @Override
    public List<Employee> getAllEmployeesByDepartmentId(UUID departmentId) {
        List<Employee> employees = employeeRepository.findByDepartmentId(departmentId);

        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException(departmentId.toString());
        }
        return employees;
    }

    @Override
    public Employee getEmployeeByDepartmentId(UUID departmentId, UUID employeeId) {
        return employeeRepository.findByIdAndDepartmentId(departmentId, employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(departmentId, employeeId));
    }

    @Override
    public Employee updateEmployeeByDepartmentId(UUID departmentId, UUID employeeId, Employee employee) {
        Employee oldEmployee = getEmployeeByDepartmentId(departmentId, employeeId);
        oldEmployee.setVaccinationStatus(employee.getVaccinationStatus());
        oldEmployee.setVaccinationBrand(employee.getVaccinationBrand());
        oldEmployee.setHealthStatus(employee.getHealthStatus());

        return employeeRepository.save(oldEmployee);
    }
}
