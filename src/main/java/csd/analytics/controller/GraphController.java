package csd.analytics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import csd.analytics.model.BarGraph;
import csd.analytics.model.LineGraph;
import csd.analytics.service.GraphService;

@RestController
public class GraphController {
    private GraphService graphService;

    @Autowired
    public GraphController(GraphService graphService) {
        this.graphService = graphService;
    }

    @GetMapping("/graph/bar")
    public List<BarGraph> getBarGraph() {
        return graphService.getBarGraph();
    }

    @GetMapping("/graph/line")
    public List<LineGraph> getLineGraph() {
        return graphService.getLineGraph();
    }
}
