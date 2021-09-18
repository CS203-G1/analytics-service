package csd.analytics.service;

import csd.analytics.model.EmployeeVaccination;

import java.util.List;
import java.util.UUID;

public interface EmployeeVaccinationService {
    EmployeeVaccination addEmployeeVaccination(UUID employeeId, EmployeeVaccination employeeVaccination);

    List<EmployeeVaccination> getEmployeeVaccinations(UUID employeeId);

    // EmployeeVaccination updateEmployeeVaccination(UUID employeeId, EmployeeVaccination employeeVaccination);

    // void deleteEmployeeVaccination(UUID employeeId);
}
