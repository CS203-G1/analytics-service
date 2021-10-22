package csd.analytics.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csd.analytics.model.CovidData;
import csd.analytics.repository.CovidDataRepository;

@Service
public class CovidDataServiceImpl implements CovidDataService {
    private CovidDataRepository covidDataRepository;

    @Autowired
    public CovidDataServiceImpl(CovidDataRepository covidDataRepository) {
        this.covidDataRepository = covidDataRepository;
    }
    
    @Override
    public List<CovidData> getAllCovidDataByOneMonth() {
        LocalDateTime end = LocalDateTime.now();
        LocalDateTime start = end.minusMonths(1);

        return covidDataRepository.findByCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(start, end);
    }
}
