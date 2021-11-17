package csd.analytics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import csd.analytics.model.CovidData;
import csd.analytics.service.CovidDataService;

@RestController
public class CovidDataController {
    private CovidDataService covidDataService;

    @Autowired
    public CovidDataController(CovidDataService covidDataService) {
        this.covidDataService = covidDataService;
    }

    @GetMapping("/covid-data/month")
    public List<CovidData> getEmployeeById() {
        return covidDataService.getAllCovidDataByOneMonth();
    }
}
