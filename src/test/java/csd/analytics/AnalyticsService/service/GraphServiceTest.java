package csd.analytics.AnalyticsService.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import csd.analytics.repository.EmployeeVaccinationRepository;
import csd.analytics.service.EmployeeServiceImpl;
import csd.analytics.service.EmployeeVaccinationServiceImpl;

@ExtendWith(MockitoExtension.class)
public class GraphServiceTest {
    @Mock
    EmployeeVaccinationRepository employeeVaccinations;

    @Mock
    EmployeeServiceImpl employeeService;

    @InjectMocks
    EmployeeVaccinationServiceImpl employeeVaccinationService;

}