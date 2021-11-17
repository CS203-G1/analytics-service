package csd.analytics.service;

import java.util.List;
import java.util.UUID;

import csd.analytics.model.EmployeeVaccination;

public interface EmployeeVaccinationService {
    List<EmployeeVaccination> getEmployeeVaccinations(UUID employeeId);

    EmployeeVaccination getEmployeeVaccination(UUID employeeId, UUID employeeVaccinationId);

    EmployeeVaccination getEmployeeVaccination(UUID employeeVaccinationId);
}
