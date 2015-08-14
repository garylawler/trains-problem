package com.service;

import com.model.Station;
import com.facade.GraphFacade;
import com.exception.PathNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class RailwayService {
    private GraphFacade<Station> stationGraphFacade;

    public RailwayService(GraphFacade<Station> graphFacade) {
        this.stationGraphFacade = graphFacade;
    }

    public double getRouteLength(Station... stations) throws PathNotFoundException {
        double totalDistance = 0;
        int numberOfStations = stations.length;
        if(numberOfStations > 1) {
            for(int i=1; i< numberOfStations; i++) {
                totalDistance += stationGraphFacade.getEdgeWeight(stations[i - 1], stations[i]);
            }
        }
        return totalDistance;
    }

    private double getCycleLength(List<Station> stations) {
        int lengthOfCycle = 0;
        int numberOfStations = stations.size();
        if(numberOfStations > 1) {
            for(int i=1; i< numberOfStations; i++) {
                lengthOfCycle += stationGraphFacade.getEdgeWeight(stations.get(i - 1), stations.get(i));
            }
            lengthOfCycle += stationGraphFacade.getEdgeWeight(stations.get(numberOfStations - 1), stations.get(0));
        }
        return lengthOfCycle;
    }

    public double getShortestNonLoopingRouteLength(Station startStation, Station endStation) {
        return stationGraphFacade.getShortestAcyclicPathLength(startStation, endStation);
    }

    public double getNumberOfRoutesWithStops(Station startStation, Station endStation, int numberOfStops) {
        return stationGraphFacade.countPathsWithStops(startStation, endStation, numberOfStops);
    }

    public double getNumberOfRoutesWithExactNumberOfStops(Station startStation, Station endStation, int numberOfStops ) {
        return stationGraphFacade.countPathsWithExactVertexs(startStation, endStation, numberOfStops);
    }

    public double getShortestLoopLengthIncludingGivenStation(Station vertex) {
        double currentShortestCycleLength = Double.MAX_VALUE;
        for(List<Station> stationList : stationGraphFacade.getCycles()) {
            if (stationList.contains(vertex)) {
                double latestCycleLength = getCycleLength(stationList);
                currentShortestCycleLength = latestCycleLength < currentShortestCycleLength ? latestCycleLength : currentShortestCycleLength;
            }
        }
        return currentShortestCycleLength;
    }

    public double getNumberOfRoutesIncludingStationWithDistanceLessThanThirty(Station station) {
        List<Double> cycleLengthsUnderThirty = new ArrayList<>();
        stationGraphFacade.getCycles().stream()
                .filter(cycle -> cycle.contains(station))
                .forEach(cycle -> {
                    Double cycleLength = getCycleLength(cycle);
                    if (cycleLength < 30) {
                        cycleLengthsUnderThirty.add(cycleLength);
                    }
                });

        for(int i = 0; i < cycleLengthsUnderThirty.size(); i++) {
            for(int j = 0; j< cycleLengthsUnderThirty.size(); j++) {
                double total = cycleLengthsUnderThirty.get(i) + cycleLengthsUnderThirty.get(j);
                if(total < 30 &&
                        !(cycleLengthsUnderThirty.get(i) != cycleLengthsUnderThirty.get(j) &&
                                cycleLengthsUnderThirty.get(i) % cycleLengthsUnderThirty.get(j) == 0)) {
                    cycleLengthsUnderThirty.add(total);
                }
            }
        }

        return cycleLengthsUnderThirty.size();
    }
}
