package com.example.AnalyticsService.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import csd.analytics.enumerator.HealthStatus;
import csd.analytics.enumerator.VaccinationStatus;
import csd.analytics.model.Employee;
import csd.analytics.model.EmployeeVaccination;
import csd.analytics.repository.EmployeeVaccinationRepository;
import csd.analytics.service.EmployeeServiceImpl;
import csd.analytics.service.EmployeeVaccinationServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EmployeeVaccinationServiceTest {
    @Mock
    EmployeeVaccinationRepository employeeVaccinations;

    @Mock
    EmployeeServiceImpl employeeService;

    @InjectMocks
    EmployeeVaccinationServiceImpl employeeVaccinationService;

    @Test
    public void getEmployeeVaccinations_EmployeeVaccinationsExist_ReturnListOfEmployeeVaccinations() {
        UUID employeeId = UUID.randomUUID();
        List<EmployeeVaccination> allEmployeeVaccinationsInEmployee = new ArrayList<EmployeeVaccination>();
        VaccinationStatus vaccinationStatus = VaccinationStatus.NOT_VACCINATED;
        HealthStatus healthStatus = HealthStatus.COVID;
        Employee employee = new Employee(employeeId, allEmployeeVaccinationsInEmployee, null, "John Doe", vaccinationStatus, null, healthStatus, LocalDateTime.now());

        UUID employeeVaccinationId = UUID.randomUUID();
        EmployeeVaccination employeeVaccination = new EmployeeVaccination(employeeVaccinationId, employee, null, 0, LocalDateTime.now());

        allEmployeeVaccinationsInEmployee.add(employeeVaccination);

        when(employeeVaccinations.findByEmployeeId(employeeId)).thenReturn(employee.getEmployeeVaccinations());

        List<EmployeeVaccination> employeeVaccinationsForEmployee = employeeVaccinationService.getEmployeeVaccinations(employeeId);

        assertNotNull(employeeVaccinationsForEmployee);
        assertEquals(1, employeeVaccinationsForEmployee.size());

        verify(employeeVaccinations, times(1)).findByEmployeeId(employeeId);
    }
}
