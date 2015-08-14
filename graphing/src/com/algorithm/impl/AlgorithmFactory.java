package com.algorithm.impl;

import com.algorithm.*;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class AlgorithmFactory<V> {

    private SimpleDirectedWeightedGraph<V, DefaultWeightedEdge> graph;

    public AlgorithmFactory(SimpleDirectedWeightedGraph<V, DefaultWeightedEdge> graph) {
        this.graph = graph;
    }

    public ShortestPath<V> createShortestPathAlgorithm(V startVertex, V endVertex) {
        return new DijkstraShortestPathDelegate<>(graph, startVertex, endVertex);
    }

    public CycleCount<V> createCycleCountAlgorithm() {
        return new TarjanSimpleCyclesDelegate<>(graph);
    }

    public CountPathsWithNumberOfStops<V, DefaultWeightedEdge> createCountPathsWithNumberOfStops(V startVertex, V endVertex, int maxDepth) {
        return new CountPathsWithNumberOfStopsImpl<>(graph, startVertex, endVertex, maxDepth);
    }

    public CountPathsWithAtLeastAGivenNumberOfStops<V, DefaultWeightedEdge> createCountPathsWithAtLeastAGivenNumberOfStops(V startVertex, V endVertex, int maxDepth) {
        return new CountPathsWithAtLeastAGivenNumberOfStopsImpl<>(graph, startVertex, endVertex, maxDepth);
    }
}
