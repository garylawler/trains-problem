package com.facade;

import com.algorithm.CountPathsWithAtLeastAGivenNumberOfStops;
import com.algorithm.CountPathsWithNumberOfStops;
import com.exception.PathNotFoundException;
import com.factory.AlgorithmFactory;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.alg.cycle.TarjanSimpleCycles;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import java.util.List;

public class GraphFacade<V> {
    private SimpleDirectedWeightedGraph<V, DefaultWeightedEdge> directedGraph;
    private AlgorithmFactory<V> algorithmFactory;

    public GraphFacade(SimpleDirectedWeightedGraph<V, DefaultWeightedEdge> directedGraph, AlgorithmFactory<V> algorithmFactory) {
        this.directedGraph = directedGraph;
        this.algorithmFactory = algorithmFactory;
    }

    public double getEdgeWeight(V startNode, V endNode) throws PathNotFoundException{
        DefaultWeightedEdge selectedEdge = directedGraph.getEdge(startNode, endNode);
        if(selectedEdge != null) {
            return directedGraph.getEdgeWeight(selectedEdge);
        }
        throw new PathNotFoundException(startNode, endNode);
    }

    public void addEdge(V startNode, V endNode, int weight) {
        addVertex(startNode);
        addVertex(endNode);
        directedGraph.setEdgeWeight(directedGraph.addEdge(startNode, endNode), weight);
    }

    public double getShortestAcyclicPathLength(V startNode, V endNode) {
        DijkstraShortestPath<V, DefaultWeightedEdge> dijkstraShortestPath = algorithmFactory.createDijkstraShortestPath(startNode, endNode);
        return dijkstraShortestPath.getPathLength();
    }

    public double getPathsWithStops(V startNode, V endNode, int numberOfStops ) {
        CountPathsWithAtLeastAGivenNumberOfStops<V, DefaultWeightedEdge> depthFirstPathCounter2 = algorithmFactory.createCountPathsWithAtLeastAGivenNumberOfStops(startNode, endNode, numberOfStops);
        depthFirstPathCounter2.traverse();
        return depthFirstPathCounter2.getNoOfRoutes();
    }

    public double getPathsWithExactNodes(V startNode, V endNode, int numberOfStops ) {
        CountPathsWithNumberOfStops<V, DefaultWeightedEdge> depthFirstPathCounter = algorithmFactory.createCountPathsWithNumberOfStops(startNode, endNode, numberOfStops);
        depthFirstPathCounter.traverse();
        return depthFirstPathCounter.getNoOfRoutesAtTargetDepth();
    }

    public List<List<V>> getCycles() {
        TarjanSimpleCycles<V, DefaultWeightedEdge> simpleCycles = algorithmFactory.createTarjanSimpleCycles();
        return simpleCycles.findSimpleCycles();
    }

    private void addVertex(V vertex) {
        if(!directedGraph.containsVertex(vertex)) {
            directedGraph.addVertex(vertex);
        }
    }
}