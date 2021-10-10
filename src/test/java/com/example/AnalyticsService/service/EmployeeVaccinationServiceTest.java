package com.example.AnalyticsService.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
}
