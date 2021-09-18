package csd.analytics.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND) // 404 Error
public class EmployeeVaccinationNotFoundException extends ResourceNotFoundException {
    private static final long serialVersionUID = 1L;

    public EmployeeVaccinationNotFoundException(UUID employeeId) {
        super(String.format("Employee %s does not have any vaccinations", employeeId));
    }
}