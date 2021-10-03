package csd.analytics.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csd.analytics.model.Employee;
import csd.analytics.repository.EmployeeRepository;
import csd.analytics.repository.StatisticsRepository;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    private StatisticsRepository statisticsRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public StatisticsServiceImpl(StatisticsRepository statisticsRepository, EmployeeRepository employeeRepository) {
        this.statisticsRepository = statisticsRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void insertSnapshot() {

    }

    @Override
    public double employeeTurnoverRate() {
        /**
         * 1. Get all departments from companyId
         * 2. Get all employees belonging to those departments
         * 3. Compute turnover rate per month for the company
         */

        Calendar calendar = Calendar.getInstance();
        Date end = calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH, 1); // Set day of start date to 1
        Date start = calendar.getTime();

        List<Employee> employees = employeeRepository.findByCreatedAtBetween(start, end);
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
}
