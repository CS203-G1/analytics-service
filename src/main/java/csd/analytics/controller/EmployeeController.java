package csd.analytics.controller;

import csd.analytics.exception.EmployeeNotFoundException;
import csd.analytics.exception.ResourceNotFoundException;
import csd.analytics.model.Employee;
import csd.analytics.service.EmployeeService;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable UUID id) {
        try {
            return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/employees/{id}")
    public void delete(@PathVariable(value = "employeeId") UUID id) {
        try {
            employeeService.deleteEmployeeByid(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EmployeeNotFoundException(id);
        }
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }
}
