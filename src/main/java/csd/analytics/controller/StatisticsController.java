package csd.analytics.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import csd.analytics.service.StatisticsService;

@RestController
public class StatisticsController {
    private StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    //TODO - add in companyId into path variable
    @GetMapping("/statistics/{companyId}/turnover")
    public double getEmployeeTurnoverRate(@PathVariable(name = "companyID") UUID companyId) {
        return statisticsService.employeeTurnoverRate(companyId);
    }
}
