package csd.analytics.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import csd.analytics.exception.EmployeeNotFoundException;
import csd.analytics.model.Employee;
import csd.analytics.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

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
    public Employee getEmployeeById(UUID id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public Employee updateEmployeeByid(UUID id, Employee employee) {
        return employeeRepository.findById(id).map(oldEmployee -> {
            oldEmployee.setVaccinationStatus(employee.getVaccinationStatus());
            oldEmployee.setVaccinationBrand(employee.getVaccinationBrand());
            oldEmployee.setHealthStatus(employee.getHealthStatus());

            return employeeRepository.save(oldEmployee);
        }).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public void deleteEmployeeByid(UUID id) {
        Employee employee = getEmployeeById(id);
        
        employeeRepository.delete(employee);
    }
}
