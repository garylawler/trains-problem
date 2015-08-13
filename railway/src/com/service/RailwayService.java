package com.service;

import com.model.Station;
import com.facade.GraphFacade;
import com.exception.PathNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class RailwayService {
    private GraphFacade<Station> railwayGraph;

    public RailwayService(GraphFacade<Station> graphFacade) {
        this.railwayGraph = graphFacade;
    }

    public double getRouteLength(Station... stations) throws PathNotFoundException {
        double distance = 0;
        int numberOfNodes = stations.length;
        if(numberOfNodes > 1) {
            for(int i=1; i< numberOfNodes; i++) {
                distance += railwayGraph.getEdgeWeight(stations[i - 1], stations[i]);
            }
        }
        return distance;
    }

    public double getShortestNonLoopingRouteLength(Station startStation, Station endStation) {
        return railwayGraph.getShortestAcyclicPathLength(startStation, endStation);
    }

    public double getPathsWithStops(Station startStation, Station endStation, int numberOfStops ) {
        return railwayGraph.getPathsWithStops(startStation, endStation, numberOfStops);
    }

    public double getNumberOfRoutesWithExactNumberOfStops(Station startStation, Station endStation, int numberOfStops ) {
        return railwayGraph.getPathsWithExactNodes(startStation, endStation, numberOfStops);
    }

    public double getCycleLength(List<Station> nodes) {
        int lengthOfCycle = 0;
        int numberOfNodes = nodes.size();
        if(numberOfNodes > 1) {
            for(int i=1; i< numberOfNodes; i++) {
                lengthOfCycle += railwayGraph.getEdgeWeight(nodes.get(i - 1), nodes.get(i));
            }
            lengthOfCycle += railwayGraph.getEdgeWeight(nodes.get(numberOfNodes - 1), nodes.get(0));
        }
        return lengthOfCycle;
    }

    public double getShortestLoopLengthIncludingGivenStation(Station vertex) {
        double cycleLength = Double.MAX_VALUE;
        for(List<Station> nodeList : railwayGraph.getCycles()) {
            if (nodeList.contains(vertex)) {
                double latestCycleLength = getCycleLength(nodeList);
                cycleLength = latestCycleLength < cycleLength ? latestCycleLength : cycleLength;
            }
        }
        return cycleLength;
    }

    public double x() {
//        class computedPath {
//            public computedPath(double length, boolean )
//        }

        List<Double> pathLengthsUnderThirty = new ArrayList<>();
        railwayGraph.getCycles().stream()
                .filter(cycle -> cycle.contains(Station.C))
                .forEach(cycle -> {
                    Double cycleLength = getCycleLength(cycle);
                    if (cycleLength < 30) {
                        pathLengthsUnderThirty.add(cycleLength);
                    }
                });

        for(int i = 0; i < pathLengthsUnderThirty.size(); i++) {
            for(int j = 0; j< pathLengthsUnderThirty.size(); j++) {
                double total = pathLengthsUnderThirty.get(i) + pathLengthsUnderThirty.get(j);

                System.out.println(pathLengthsUnderThirty.get(i) + " + " + pathLengthsUnderThirty.get(j) + " = " + total);

                if(total < 30 && // This clause fails if two distinct paths have the same length
                        !(pathLengthsUnderThirty.get(i) != pathLengthsUnderThirty.get(j) &&
                                pathLengthsUnderThirty.get(i) % pathLengthsUnderThirty.get(j) == 0)) {
                    System.out.println("acceptable\n");
                    pathLengthsUnderThirty.add(total);
                }
            }
        }

        return pathLengthsUnderThirty.size();
    }
}
