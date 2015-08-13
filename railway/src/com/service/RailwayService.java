package com.service;

import com.Station;
import com.component.GraphFacade;
import com.exception.PathNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class RailwayService {
    private GraphFacade<Station> railway;

    public RailwayService(GraphFacade graphFacade) {
        this.railway = graphFacade;
    }

    public double getRouteLength(Station... stations) throws PathNotFoundException {
        double distance = 0;
        int numberOfNodes = stations.length;
        if(numberOfNodes > 1) {
            for(int i=1; i< numberOfNodes; i++) {
                distance += railway.getEdgeWeight(stations[i - 1], stations[i]);
            }
        }
        return distance;
    }

    public double getPathLengthWithoutLoops(Station startStation, Station endStation) {
        return railway.getShortestAcyclicPathLength(startStation, endStation);
    }

    public double getPathsWithStops(Station startStation, Station endStation, int numberOfStops ) {
        return railway.getPathsWithStops(startStation, endStation, numberOfStops);
    }

    public double getPathsWithExactNodes(Station startStation, Station endStation, int numberOfStops ) {
        return railway.getPathsWithExactNodes(startStation, endStation, numberOfStops);
    }

    public List<Station> getCycleContainingNode(String node) {
        for(List<Station> nodeList : railway.getCycles()) {
            if(nodeList.contains(node)) {
                return nodeList;
            }
        }
        return new ArrayList<>();
    }

    public double getCycleLength(List<Station> nodes) {
        int lengthOfCycle = 0;
        int numberOfNodes = nodes.size();
        if(numberOfNodes > 1) {
            for(int i=1; i< numberOfNodes; i++) {
                lengthOfCycle += railway.getEdgeWeight(nodes.get(i - 1), nodes.get(i));
            }
            lengthOfCycle += railway.getEdgeWeight(nodes.get(numberOfNodes - 1), nodes.get(0));
        }
        return lengthOfCycle;

    }

    public double getShortestLoopLengthIncludingGivenStation(Station vertex) {
        double cycleLength = Double.MAX_VALUE;
        for(List<Station> nodeList : railway.getCycles()) {
            if(nodeList.contains(vertex)) {
                double latestCycleLength = getCycleLength(nodeList);
                cycleLength = latestCycleLength < cycleLength ? latestCycleLength : cycleLength;
            }
        }
        return cycleLength;
    }
}
