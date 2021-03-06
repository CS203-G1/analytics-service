package csd.analytics.AnalyticsService.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import csd.analytics.enumerator.HealthStatus;
import csd.analytics.enumerator.VaccinationStatus;
import csd.analytics.exception.EmployeeVaccinationNotFoundException;
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
        Employee employee = new Employee(employeeId, allEmployeeVaccinationsInEmployee, null, "John Doe", vaccinationStatus, null, healthStatus, LocalDateTime.now(), true);

        UUID employeeVaccinationId = UUID.randomUUID();
        EmployeeVaccination employeeVaccination = new EmployeeVaccination(employeeVaccinationId, employee, null, 0, LocalDateTime.now());

        allEmployeeVaccinationsInEmployee.add(employeeVaccination);

        when(employeeVaccinations.findByEmployeeId(employeeId)).thenReturn(employee.getEmployeeVaccinations());

        List<EmployeeVaccination> employeeVaccinationsForEmployee = employeeVaccinationService.getEmployeeVaccinations(employeeId);

        assertNotNull(employeeVaccinationsForEmployee);
        assertEquals(1, employeeVaccinationsForEmployee.size());

        verify(employeeVaccinations, times(1)).findByEmployeeId(employeeId);
    }

    @Test
    public void getEmployeeVaccinations_EmptyEmployeeVaccinations_ThrowException() {
        UUID employeeId = UUID.randomUUID();
        List<EmployeeVaccination> allEmployeeVaccinationsInEmployee = new ArrayList<EmployeeVaccination>();
        VaccinationStatus vaccinationStatus = VaccinationStatus.NOT_VACCINATED;
        HealthStatus healthStatus = HealthStatus.COVID;
        Employee employee = new Employee(employeeId, allEmployeeVaccinationsInEmployee, null, "John Doe", vaccinationStatus, null, healthStatus, LocalDateTime.now(), true);

        when(employeeVaccinations.findByEmployeeId(employeeId)).thenReturn(employee.getEmployeeVaccinations());

        Exception exception = assertThrows(EmployeeVaccinationNotFoundException.class, () -> employeeVaccinationService.getEmployeeVaccinations(employeeId));
        String expectedExceptionMessage = String.format("Employee %s does not have any vaccinations", employeeId);

        assertEquals(expectedExceptionMessage, exception.getMessage());
        verify(employeeVaccinations, times(1)).findByEmployeeId(employeeId);
    }

    @Test
    public void getEmployeeVaccinationWithIdAndEmployeeId_EmployeeVaccinationExists_ReturnFoundEmployeeVaccination() {
        UUID employeeId = UUID.randomUUID();
        List<EmployeeVaccination> allEmployeeVaccinationsInEmployee = new ArrayList<EmployeeVaccination>();
        VaccinationStatus vaccinationStatus = VaccinationStatus.NOT_VACCINATED;
        HealthStatus healthStatus = HealthStatus.COVID;
        Employee employee = new Employee(employeeId, allEmployeeVaccinationsInEmployee, null, "John Doe", vaccinationStatus, null, healthStatus, LocalDateTime.now(), true);

        UUID employeeVaccinationId = UUID.randomUUID();
        EmployeeVaccination employeeVaccination = new EmployeeVaccination(employeeVaccinationId, employee, null, 0, LocalDateTime.now());

        when(employeeVaccinations.findByIdAndEmployeeId(employeeVaccinationId,employeeId)).thenReturn(Optional.of(employeeVaccination));

        EmployeeVaccination foundEmployeeVaccination = employeeVaccinationService.getEmployeeVaccination(employeeId, employeeVaccinationId);

        assertEquals(employeeVaccination, foundEmployeeVaccination);

        verify(employeeVaccinations, times(1)).findByIdAndEmployeeId(employeeVaccinationId, employeeId);
    }

    @Test
    public void getEmployeeVaccinationWithIdAndEmployeeId_EmployeeVaccinationDoesNotExist_ThrowException() {
        UUID employeeId = UUID.randomUUID();
        UUID employeeVaccinationId = UUID.randomUUID();

        Exception exception = assertThrows(EmployeeVaccinationNotFoundException.class, () -> employeeVaccinationService.getEmployeeVaccination(employeeId, employeeVaccinationId));
        String expectedExceptionMessage = String.format("Unable to find employee vaccination %s from employee %s",
                employeeVaccinationId, employeeId);

        assertEquals(expectedExceptionMessage, exception.getMessage());
        verify(employeeVaccinations, times(1)).findByIdAndEmployeeId(employeeVaccinationId, employeeId);
    }

    @Test
    public void getEmployeeVaccinationWithId_EmployeeVaccinationExists_ReturnFoundEmployeeVaccination() {
        UUID employeeId = UUID.randomUUID();
        List<EmployeeVaccination> allEmployeeVaccinationsInEmployee = new ArrayList<EmployeeVaccination>();
        VaccinationStatus vaccinationStatus = VaccinationStatus.NOT_VACCINATED;
        HealthStatus healthStatus = HealthStatus.COVID;
        Employee employee = new Employee(employeeId, allEmployeeVaccinationsInEmployee, null, "John Doe", vaccinationStatus, null, healthStatus, LocalDateTime.now(), true);

        UUID employeeVaccinationId = UUID.randomUUID();
        EmployeeVaccination employeeVaccination = new EmployeeVaccination(employeeVaccinationId, employee, null, 0, LocalDateTime.now());

        when(employeeVaccinations.findById(employeeVaccinationId)).thenReturn(Optional.of(employeeVaccination));

        EmployeeVaccination foundEmployeeVaccination = employeeVaccinationService.getEmployeeVaccination(employeeVaccinationId);

        assertEquals(employeeVaccination, foundEmployeeVaccination);

        verify(employeeVaccinations, times(1)).findById(employeeVaccinationId);
    }

    @Test
    public void getEmployeeVaccinationWithId_EmployeeVaccinationDoesNotExist_ThrowException() {
        UUID employeeVaccinationId = UUID.randomUUID();

        Exception exception = assertThrows(EmployeeVaccinationNotFoundException.class, () -> employeeVaccinationService.getEmployeeVaccination(employeeVaccinationId));
        String expectedExceptionMessage = String.format("EmployeeVaccination %s does not exist", employeeVaccinationId);

        assertEquals(expectedExceptionMessage, exception.getMessage());
        verify(employeeVaccinations, times(1)).findById(employeeVaccinationId);
    }
}
