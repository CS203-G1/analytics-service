package csd.analytics.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csd.analytics.enumerator.HealthStatus;
import csd.analytics.model.Employee;
import csd.analytics.model.Statistics;
import csd.analytics.repository.StatisticsRepository;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    private StatisticsRepository statisticsRepository;
    private EmployeeService employeeService;
    private DepartmentService departmentService;

    @Autowired
    public StatisticsServiceImpl(StatisticsRepository statisticsRepository, EmployeeService employeeService, DepartmentService departmentService) {
        this.statisticsRepository = statisticsRepository;
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @Override
    public void insertSnapshot(UUID companyId) {
        /**
         * Find all employees in the company
         * Insert the following data into the logs:
         *      1. Health status
         */
        List<UUID> departmentIds = departmentService.getDepartmentIdsByCompanyId(companyId);
        List<Employee> employees = employeeService.getAllEmployeesByDepartmentIds(departmentIds);

        int numOfSick = 0;
        int numOfHealthy = 0;
        int numOfCovid = 0;

        for (Employee employee: employees) {
            if (employee.getHealthStatus().equals(HealthStatus.COVID)) numOfCovid++;
            if (employee.getHealthStatus().equals(HealthStatus.HEALTHY)) numOfHealthy++;
            if (employee.getHealthStatus().equals(HealthStatus.ILL)) numOfSick++;
        }

        Statistics statistic = new Statistics();
        statistic.setNumOfSick(numOfSick);
        statistic.setNumOfHealthy(numOfHealthy);
        statistic.setNumOfCovid(numOfCovid);

        statisticsRepository.save(statistic);
    }

    //TODO - Add in companyId
    @Override
    public double employeeTurnoverRate(UUID companyId) {
        /**
         * 1. Get all departments from companyId
         * 2. Get all employees belonging to those departments
         * 3. Compute turnover rate per month for the company
         */

        List<UUID> departmentIds = departmentService.getDepartmentIdsByCompanyId(companyId);

        List<Employee> employees = employeeService.getEmployeesByCurrentMonth(departmentIds);
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
        
        return 0;
    }
}
