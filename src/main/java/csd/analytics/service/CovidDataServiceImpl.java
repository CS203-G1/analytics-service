package csd.analytics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import csd.analytics.model.CovidData;
import csd.analytics.repository.CovidDataRepository;

public class CovidDataServiceImpl implements CovidDataService {
    private CovidDataRepository covidDataRepository;

    @Autowired
    public CovidDataServiceImpl(CovidDataRepository covidDataRepository) {
        this.covidDataRepository = covidDataRepository;
    }
    
    @Override
    public List<CovidData> getAllCovidDataByOneMonth() {
        return null;
    }
}
