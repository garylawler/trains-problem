package com.component;

import com.algorithm.CountPathsWithNumberOfStops;
import com.algorithm.CountPathsWithAtLeastAGivenNumberOfStops;
import com.factory.AlgorithmFactory;
import com.exception.PathNotFoundException;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.alg.cycle.TarjanSimpleCycles;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import java.util.ArrayList;
import java.util.List;

public class GraphFacade {
    private SimpleDirectedWeightedGraph<NamedVertex, DefaultWeightedEdge> directedGraph;
    private AlgorithmFactory algorithmFactory;

    public GraphFacade(SimpleDirectedWeightedGraph<NamedVertex, DefaultWeightedEdge> directedGraph, AlgorithmFactory algorithmFactory) {
        this.directedGraph = directedGraph;
        this.algorithmFactory = algorithmFactory;
    }

    public double getEdgeWeight(NamedVertex startNode, NamedVertex endNode) throws PathNotFoundException{
        DefaultWeightedEdge selectedEdge = directedGraph.getEdge(startNode, endNode);
        if(selectedEdge != null) {
            return directedGraph.getEdgeWeight(selectedEdge);
        }
        throw new PathNotFoundException(startNode, endNode);
    }

    public void addEdge(NamedVertex startNode, NamedVertex endNode, int weight) {
        addVertex(startNode);
        addVertex(endNode);
        directedGraph.setEdgeWeight(directedGraph.addEdge(startNode, endNode), weight);
    }

    public double getShortestAcyclicPathLength(NamedVertex startNode, NamedVertex endNode) {
        DijkstraShortestPath<NamedVertex, DefaultWeightedEdge> dijkstraShortestPath = algorithmFactory.createDijkstraShortestPath(startNode, endNode);
        return dijkstraShortestPath.getPathLength();
    }

    public double getPathsWithStops(NamedVertex startNode, NamedVertex endNode, int numberOfStops ) {
        CountPathsWithAtLeastAGivenNumberOfStops<NamedVertex, DefaultWeightedEdge> depthFirstPathCounter2 = algorithmFactory.createCountPathsWithAtLeastAGivenNumberOfStops(startNode, endNode, numberOfStops);
        depthFirstPathCounter2.traverse();
        return depthFirstPathCounter2.getNoOfRoutes();
    }

    public double getPathsWithExactNodes(NamedVertex startNode, NamedVertex endNode, int numberOfStops ) {
        CountPathsWithNumberOfStops<NamedVertex, DefaultWeightedEdge> depthFirstPathCounter = algorithmFactory.createCountPathsWithNumberOfStops(startNode, endNode, numberOfStops);
        depthFirstPathCounter.traverse();
        return depthFirstPathCounter.getNoOfRoutesAtTargetDepth();
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
                lengthOfCycle += getEdgeWeight(nodes.get(i-1), nodes.get(i));
            }
            lengthOfCycle += getEdgeWeight(nodes.get(numberOfNodes-1), nodes.get(0));
        }
        return lengthOfCycle;

    }

    public List<List<NamedVertex>> getCycles() {
        TarjanSimpleCycles<NamedVertex, DefaultWeightedEdge> simpleCycles = algorithmFactory.createTarjanSimpleCycles();
        return simpleCycles.findSimpleCycles();
    }

    private void addVertex(NamedVertex vertex) {
        if(!directedGraph.containsVertex(vertex)) {
            directedGraph.addVertex(vertex);
        }
    }
}
