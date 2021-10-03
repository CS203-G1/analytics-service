package csd.analytics.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public class HealthCheckController {
    @GetMapping("/healthcheck")
    public ResponseEntity<String> getHealthCheck() {
        return new ResponseEntity<String>("Webscraper service is healthy", HttpStatus.OK);
    }
}
