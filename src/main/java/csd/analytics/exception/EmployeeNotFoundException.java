package csd.analytics.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND) // 404 Error
public class EmployeeNotFoundException extends ResourceNotFoundException {
    private static final long serialVersionUID = 1L;

    public EmployeeNotFoundException(UUID departmentId, UUID employeeId) {
        super(String.format("Could not find employee %s from department %s", employeeId, departmentId));
    }

    // Overloaded constructor for departments with no employees
    public EmployeeNotFoundException(String departmentId) {
        super(String.format("Department %s does not have any employees", departmentId));
    }

    public EmployeeNotFoundException(UUID employeeId) {
        super(String.format("Unable to find", employeeId));
    }
}
