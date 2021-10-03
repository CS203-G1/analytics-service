package csd.analytics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping("/statistics/turnover")
    public double getEmployeeTurnoverRate() {
        return statisticsService.employeeTurnoverRate();
    }
}
