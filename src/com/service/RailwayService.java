package com.service;

import com.component.GraphFacade;
import com.component.NamedVertex;
import com.exception.PathNotFoundException;
import com.factory.AlgorithmFactory;
import org.jgrapht.alg.cycle.TarjanSimpleCycles;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.ArrayList;
import java.util.List;

public class RailwayService {
    private GraphFacade railway;
    private AlgorithmFactory algorithmFactory;

    public RailwayService(GraphFacade graphFacade, AlgorithmFactory algorithmFactory) {
        this.railway = graphFacade;
        this.algorithmFactory = algorithmFactory;
    }

    public double getRouteLength(NamedVertex... stations) throws PathNotFoundException {
        double distance = 0;
        int numberOfNodes = stations.length;
        if(numberOfNodes > 1) {
            for(int i=1; i< numberOfNodes; i++) {
                distance += railway.getEdgeWeight(stations[i - 1], stations[i]);
            }
        }
        return distance;
    }

    public double getShortestAcyclicPathLength(NamedVertex startStation, NamedVertex endStation) {
        return railway.getShortestAcyclicPathLength(startStation, endStation);
    }

    public double getPathsWithStops(NamedVertex startStation, NamedVertex endStation, int numberOfStops ) {
        return railway.getPathsWithStops(startStation, endStation, numberOfStops);
    }

    public double getPathsWithExactNodes(NamedVertex startStation, NamedVertex endStation, int numberOfStops ) {
        return railway.getPathsWithExactNodes(startStation, endStation, numberOfStops);
    }

    public List<NamedVertex> getCycleContainingNode(String node) {
        for(List<NamedVertex> nodeList : getCycles()) {
            if(nodeList.contains(node)) {
                return nodeList;
            }
        }
        return new ArrayList<>();
    }

    public double getCycleLength(List<NamedVertex> nodes) {
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

    public List<NamedVertex> getCycleIncludingVertex(NamedVertex vertex) {
        double cycleLength = Double.MAX_VALUE;
        for(List<NamedVertex> nodeList : getCycles()) {
            if(nodeList.contains(vertex)) {
                double latestCycleLength = getCycleLength(nodeList);
                cycleLength = latestCycleLength < cycleLength ? latestCycleLength : cycleLength;
            }
        }
System.out.println(cycleLength);
        return new ArrayList<>();
    }

    public List<List<NamedVertex>> getCycles() {
        TarjanSimpleCycles<NamedVertex, DefaultWeightedEdge> simpleCycles = algorithmFactory.createTarjanSimpleCycles();

        //getCycleIncludingVertex(NamedVertex.B);

        return simpleCycles.findSimpleCycles();
    }
}
