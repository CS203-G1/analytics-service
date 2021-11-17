package csd.analytics.service;

import java.util.List;

import csd.analytics.model.BarGraph;
import csd.analytics.model.LineGraph;

public interface GraphService {
    List<BarGraph> getBarGraph();
    List<LineGraph> getLineGraph();
    double convertToPercentage(int numerator, int denominator);
}
