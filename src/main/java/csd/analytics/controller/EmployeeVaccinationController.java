package csd.analytics.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import csd.analytics.model.EmployeeVaccination;
import csd.analytics.service.EmployeeVaccinationService;

@RestController
public class EmployeeVaccinationController {
    private EmployeeVaccinationService employeeVaccinationService;

    @Autowired
    public EmployeeVaccinationController(EmployeeVaccinationService employeeVaccinationService) {
        this.employeeVaccinationService = employeeVaccinationService;
    }

    @GetMapping("/employees/{employeeId}/employee-vaccinations/{employeeVaccinationId}")
    public EmployeeVaccination getEmployeeVaccination(@PathVariable(value = "employeeId") UUID employeeId,
                            @PathVariable(value = "employeeVaccinationId") UUID employeeVaccinationId) {
        return employeeVaccinationService.getEmployeeVaccination(employeeId, employeeVaccinationId);
    }

    @GetMapping("/employees/{employeeId}/employee-vaccinations")
    public List<EmployeeVaccination> getEmployeeVaccinations(@PathVariable(value = "employeeId") UUID employeeId) {
        return employeeVaccinationService.getEmployeeVaccinations(employeeId);
    }
}
