package csd.analytics.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csd.analytics.exception.CovidDataNotFoundException;
import csd.analytics.model.CovidData;
import csd.analytics.repository.CovidDataRepository;
import csd.analytics.util.ClockUtil;

@Service
public class CovidDataServiceImpl implements CovidDataService {
    private CovidDataRepository covidDataRepository;
    private ClockUtil clockUtil;

    @Autowired
    public CovidDataServiceImpl(CovidDataRepository covidDataRepository, ClockUtil clockUtil) {
        this.covidDataRepository = covidDataRepository;
        this.clockUtil = clockUtil;
    }
    
    @Override
    public List<CovidData> getAllCovidDataByOneMonth() {
        LocalDateTime end = LocalDateTime.now(clockUtil.getClock());
        LocalDateTime start = end.minusMonths(1);

        List<CovidData> covidData = covidDataRepository.findByCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(start, end);

        if (covidData == null || covidData.isEmpty()) throw new CovidDataNotFoundException();

        return covidData;
    }
}
