package csd.analytics.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import csd.analytics.model.Employee;
import csd.analytics.service.EmployeeService;

@RestController
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/departments/{departmentId}/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable(value = "departmentId") UUID departmentId,
            @PathVariable(value = "employeeId") UUID employeeId) {
        return employeeService.getEmployeeByDepartmentId(departmentId, employeeId);
    }

    @GetMapping("/departments/{departmentId}/employees")
    public List<Employee> getAllEmployees(@PathVariable(value = "departmentId") UUID departmentId) {
        return employeeService.getAllEmployeesByDepartmentId(departmentId);
    }

    @PutMapping("/departments/{departmentId}/employees/{employeeId}")
    public Employee updateEmployee(@PathVariable(value = "departmentId") UUID departmentId,
                                    @PathVariable(value = "employeeId") UUID employeeId, 
                                    @Valid @RequestBody Employee employee) {
        return employeeService.updateEmployeeByDepartmentId(departmentId, employeeId, employee);
    }
}
