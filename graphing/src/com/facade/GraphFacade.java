package com.facade;

import com.algorithm.CountPathsWithAtLeastAGivenNumberOfStops;
import com.algorithm.CountPathsWithNumberOfStops;
import com.algorithm.CycleCount;
import com.algorithm.ShortestPath;
import com.algorithm.impl.AlgorithmFactory;
import com.exception.PathNotFoundException;
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

    public double getEdgeWeight(V startVertex, V endVertex) throws PathNotFoundException{
        DefaultWeightedEdge selectedEdge = directedGraph.getEdge(startVertex, endVertex);
        if(selectedEdge != null) {
            return directedGraph.getEdgeWeight(selectedEdge);
        }
        throw new PathNotFoundException(startVertex, endVertex);
    }

    public void addEdge(V startVertex, V endVertex, int weight) {
        addVertex(startVertex);
        addVertex(endVertex);
        directedGraph.setEdgeWeight(directedGraph.addEdge(startVertex, endVertex), weight);
    }

    public double getShortestAcyclicPathLength(V startVertex, V endVertex) {
        ShortestPath<V> shortestPath = algorithmFactory.createShortestPathAlgorithm(startVertex, endVertex);
        return shortestPath.getShortestPathLength();
    }

    public double countPathsWithStops(V startVertex, V endVertex, int numberOfStops) {
        CountPathsWithAtLeastAGivenNumberOfStops<V, DefaultWeightedEdge> depthFirstPathCounter2 = algorithmFactory.createCountPathsWithAtLeastAGivenNumberOfStops(startVertex, endVertex, numberOfStops);
        return depthFirstPathCounter2.getNoOfRoutes();
    }

    public double countPathsWithExactVertexs(V startVertex, V endVertex, int numberOfStops) {
        CountPathsWithNumberOfStops<V, DefaultWeightedEdge> depthFirstPathCounter = algorithmFactory.createCountPathsWithNumberOfStops(startVertex, endVertex, numberOfStops);
        return depthFirstPathCounter.getNoOfRoutesAtTargetDepth();
    }

    public List<List<V>> getCycles() {
        CycleCount<V> simpleCycles = algorithmFactory.createCycleCountAlgorithm();
        return simpleCycles.getCycles();
    }

    private void addVertex(V vertex) {
        if(!directedGraph.containsVertex(vertex)) {
            directedGraph.addVertex(vertex);
        }
    }
}
