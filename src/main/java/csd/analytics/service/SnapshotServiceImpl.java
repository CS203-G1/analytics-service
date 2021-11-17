package csd.analytics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import csd.analytics.enumerator.HealthStatus;
import csd.analytics.model.Company;
import csd.analytics.model.Employee;
import csd.analytics.model.Statistic;
import csd.analytics.repository.StatisticsRepository;

@Service
public class SnapshotServiceImpl implements SnapshotService {
    private StatisticsRepository statisticsRepository;
    private CompanyService companyService;
    private EmployeeService employeeService;

    @Autowired
    public SnapshotServiceImpl(StatisticsRepository statisticsRepository, CompanyService companyService, EmployeeService employeeService) {
        this.statisticsRepository = statisticsRepository;
        this.companyService = companyService;
        this.employeeService = employeeService;
    }

    @Scheduled(cron = "@midnight")
    @Override
    public void insertSnapshot() {
        /**
         * 1. Get all company IDs
         * 2. Get the list of employees in each company
         * 3. Insert a snapshot for each company
         */

        List<Company> companies = companyService.getAllCompany();

        for (Company company: companies) {
            List<Employee> employees = employeeService.getAllEmployeesByCompanyId(company.getId());

            int numOfSick = 0;
            int numOfHealthy = 0;
            int numOfCovid = 0;

            for (Employee employee : employees) {
                if (employee.getHealthStatus().equals(HealthStatus.COVID))
                    numOfCovid++;
                if (employee.getHealthStatus().equals(HealthStatus.HEALTHY))
                    numOfHealthy++;
                if (employee.getHealthStatus().equals(HealthStatus.ILL))
                    numOfSick++;
            }

            Statistic statistic = new Statistic();
            statistic.setNumOfSick(numOfSick);
            statistic.setNumOfHealthy(numOfHealthy);
            statistic.setNumOfCovid(numOfCovid);
            statistic.setCompany(company);
    
            statisticsRepository.save(statistic);
        }
    }
}
