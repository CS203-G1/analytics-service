package csd.analytics.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // 404 Error
public class CovidDataNotFoundException extends ResourceNotFoundException {
    private static final long serialVersionUID = 1L;

    public CovidDataNotFoundException() {
        super("Covid data not found");
    }
}
