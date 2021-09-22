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

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(UUID employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException(employeeId));
    }

    @Override
    public Employee updateEmployeeByid(UUID employeeId, Employee employee) {
        return employeeRepository.findById(employeeId).map(oldEmployee -> {
            oldEmployee.setVaccinationStatus(employee.getVaccinationStatus());
            oldEmployee.setVaccinationBrand(employee.getVaccinationBrand());
            oldEmployee.setHealthStatus(employee.getHealthStatus());

            return employeeRepository.save(oldEmployee);
        }).orElseThrow(() -> new EmployeeNotFoundException(employeeId));
    }

    @Override
    public void deleteEmployeeByid(UUID employeeId) {
        Employee employee = getEmployeeById(employeeId);
        
        employeeRepository.delete(employee);
    }
}
