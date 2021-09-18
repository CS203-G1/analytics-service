package csd.analytics.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csd.analytics.exception.EmployeeVaccinationNotFoundException;
import csd.analytics.model.EmployeeVaccination;
import csd.analytics.model.Employee;
import csd.analytics.repository.EmployeeVaccinationRepository;

import org.hibernate.cfg.NotYetImplementedException;

@Service
public class EmployeeVaccinationServiceImpl implements EmployeeVaccinationService {
    private EmployeeVaccinationRepository employeeVaccinationRepository;
    private EmployeeService employeeService;

    @Autowired
    public EmployeeVaccinationServiceImpl(EmployeeVaccinationRepository employeeVaccinationRepository, EmployeeService employeeService) {
        this.employeeVaccinationRepository = employeeVaccinationRepository;
        this.employeeService = employeeService;
    }

    @Override
    public EmployeeVaccination addEmployeeVaccination(UUID employeeId, EmployeeVaccination employeeVaccination) {
        throw new NotYetImplementedException();
    }

    @Override
    public List<EmployeeVaccination> getEmployeeVaccinations(UUID employeeId) {
        throw new NotYetImplementedException();
    }

    @Override
    public EmployeeVaccination getEmployeeVaccination(UUID employeeId, UUID employeeVaccinationId) {
        throw new NotYetImplementedException();
    }

    @Override
    public EmployeeVaccination updateEmployeeVaccination(UUID employeeId, UUID employeeVaccinationId, EmployeeVaccination employeeVaccination) {
        throw new NotYetImplementedException();
    }

    @Override
    public void deleteEmployeeVaccination(UUID employeeId, UUID employeeVaccinationId) {
        throw new NotYetImplementedException();
    }
}
