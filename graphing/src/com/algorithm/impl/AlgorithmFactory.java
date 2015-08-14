package com.algorithm.impl;

import com.algorithm.*;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class AlgorithmFactory<V> {

    private SimpleDirectedWeightedGraph<V, DefaultWeightedEdge> graph;

    public AlgorithmFactory(SimpleDirectedWeightedGraph<V, DefaultWeightedEdge> graph) {
        this.graph = graph;
    }

    public ShortestPath<V> createShortestPathAlgorithm(V startNode, V endNode) {
        return new DijkstraShortestPathDelegate<>(graph, startNode, endNode);
    }

    public CycleCount<V> createCycleCountAlgorithm() {
        return new TarjanSimpleCyclesDelegate<>(graph);
    }

    public CountPathsWithNumberOfStops<V, DefaultWeightedEdge> createCountPathsWithNumberOfStops(V startNode, V endNode, int maxDepth) {
        return new CountPathsWithNumberOfStopsImpl<>(graph, startNode, endNode, maxDepth);
    }

    public CountPathsWithAtLeastAGivenNumberOfStops<V, DefaultWeightedEdge> createCountPathsWithAtLeastAGivenNumberOfStops(V startNode, V endNode, int maxDepth) {
        return new CountPathsWithAtLeastAGivenNumberOfStopsImpl<>(graph, startNode, endNode, maxDepth);
    }
}
