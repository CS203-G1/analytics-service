package csd.analytics.service;

import java.util.Calendar;
import java.util.Date;
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
    public List<Employee> getAllEmployeesByDepartmentIds(List<UUID> departmentIds) {
        return employeeRepository.findByDepartmentIdIn(departmentIds);
    }

    @Override
    public Employee getEmployeeByDepartmentId(UUID departmentId, UUID employeeId) {
        return employeeRepository.findByIdAndDepartmentId(departmentId, employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(departmentId, employeeId));
    }

    @Override
    public List<Employee> getEmployeesByCurrentMonth(List<UUID> departmentIds) {
        Calendar calendar = Calendar.getInstance();
        Date end = calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH, 1); // Set day of start date to 1
        Date start = calendar.getTime();
        return employeeRepository.findByCreatedAtBetweenAndDepartmentIdIn(start, end, departmentIds);
    }

    @Override
    public List<Employee> getEmployeesByTwoWeeks(List<UUID> departmentIds) {
        Calendar calendar = Calendar.getInstance();
        Date end = calendar.getTime();

        calendar.set(Calendar.DATE, -14); // Set day of start date to 1
        Date start = calendar.getTime();
        return employeeRepository.findByCreatedAtBetweenAndDepartmentIdIn(start, end, departmentIds);
    }

    @Override
    public List<Employee> getAllEmployeesByCompanyId(UUID companyId) {
        return employeeRepository.findAllByCompanyId(companyId);
    }
}
