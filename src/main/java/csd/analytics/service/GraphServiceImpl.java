package csd.analytics.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csd.analytics.model.BarGraph;
import csd.analytics.model.CovidData;

@Service
public class GraphServiceImpl implements GraphService {
    private CovidDataService covidDataService;

    @Autowired
    public GraphServiceImpl(CovidDataService covidDataService) {
        this.covidDataService = covidDataService;
    }

    /**
     * This method parses data from CovidData model which contains scraped data
     * and converts them into the following:
     * 1. SwabRate = TotalSwabs / TotalPopulation
     * 2. VaccinatedRate = AtLeastOneDose / TotalPopulation
     * 3. FullyVaccinatedRate = CompletedFullRegime / TotalPopulation
     * 
     * @return List of BarGraph containing the metrics to be displayed on a component in frontend
     */
    @Override
    public List<BarGraph> getBarGraph() {
        List<CovidData> covidDatas = covidDataService.getAllCovidDataByOneMonth();
        List<BarGraph> barGraphs = new ArrayList<>();

        for (CovidData covidData: covidDatas) {
            double swabRate = convertToPercentage(covidData.getTotalSwab(), covidData.getTotalPopulation());
            double vaccinatedRate = convertToPercentage(covidData.getTotalAtLeastOneDose(), covidData.getTotalPopulation());
            double fullyVaccinatedRate = convertToPercentage(covidData.getTotalCompletedFullRegimen(), covidData.getTotalPopulation());
            barGraphs.add(new BarGraph(swabRate, vaccinatedRate, fullyVaccinatedRate, covidData.getCreatedAt()));
        }

        return barGraphs;
    }
    
    @Override
    public double convertToPercentage(int numerator, int denominator) {
        return (double)numerator / denominator * 100;
    }
}
