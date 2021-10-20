package csd.analytics.service;

import java.util.List;

import csd.analytics.model.CovidData;

public interface CovidDataService {
    List<CovidData> getAllCovidDataByOneMonth();
}
