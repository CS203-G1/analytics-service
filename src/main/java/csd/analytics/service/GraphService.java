package csd.analytics.service;

import java.util.List;

import csd.analytics.model.BarGraph;

public interface GraphService {
    List<BarGraph> getBarGraph();
    double convertToPercentage(int numerator, int denominator);
}
