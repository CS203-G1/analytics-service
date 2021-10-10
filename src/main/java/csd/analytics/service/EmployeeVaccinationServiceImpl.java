package csd.analytics.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csd.analytics.exception.EmployeeVaccinationNotFoundException;
import csd.analytics.model.EmployeeVaccination;
import csd.analytics.repository.EmployeeVaccinationRepository;

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
    public List<EmployeeVaccination> getEmployeeVaccinations(UUID employeeId) {
        List<EmployeeVaccination> employeeVaccination = employeeVaccinationRepository.findByEmployeeId(employeeId);

        if (employeeVaccination.isEmpty()) {
            throw new EmployeeVaccinationNotFoundException(employeeId);
        }
        return employeeVaccination;
    }

    @Override
    public EmployeeVaccination getEmployeeVaccination(UUID employeeId, UUID employeeVaccinationId) {
        return employeeVaccinationRepository.findByIdAndEmployeeId(employeeVaccinationId, employeeId)
                .orElseThrow(() -> new EmployeeVaccinationNotFoundException(employeeVaccinationId, employeeId));
    }

    @Override
    public EmployeeVaccination getEmployeeVaccination(UUID employeeVaccinationId) {
        return employeeVaccinationRepository.findById(employeeVaccinationId)
                .orElseThrow(() -> new EmployeeVaccinationNotFoundException(employeeVaccinationId));
    }
}
