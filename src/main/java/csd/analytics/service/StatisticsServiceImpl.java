package csd.analytics.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csd.analytics.model.Employee;
import csd.analytics.repository.StatisticsRepository;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    private StatisticsRepository statisticsRepository;
    private EmployeeService employeeService;

    @Autowired
    public StatisticsServiceImpl(StatisticsRepository statisticsRepository, EmployeeService employeeService) {
        this.statisticsRepository = statisticsRepository;
        this.employeeService = employeeService;
    }

    @Override
    public void insertSnapshot() {

    }

    //TODO - Add in companyId
    @Override
    public double employeeTurnoverRate() {
        /**
         * 1. Get all departments from companyId
         * 2. Get all employees belonging to those departments
         * 3. Compute turnover rate per month for the company
         */

        List<Employee> employees = employeeService.getEmployeesByCurrentMonth();
        List<Employee> employeesLeft = employees
                .stream()
                .filter(employee -> !employee.getIsInCompany())
                .collect(Collectors.toList());
        
        double turnoverRate = 0;
        try {
            turnoverRate = employeesLeft.size() / employees.size();
        } catch (ArithmeticException e) {
            return 0;
        }
        return turnoverRate;
    }

    @Override
    public double employeeSickRate() {
        // List<Employee> employees = employeeService.getEmployeesByTwoWeeks();
        return 0;
    }
}
