package com.factory;

import com.algorithm.CountPathsWithAtLeastAGivenNumberOfStops;
import com.algorithm.CountPathsWithNumberOfStops;
import com.algorithm.CycleCountAlgorithm;
import com.algorithm.ShortestPathAlgorithm;
import com.algorithm.DijkstraShortestPathDelegate;
import com.algorithm.TarjanSimpleCyclesDelegate;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class AlgorithmFactory<V> {

    private SimpleDirectedWeightedGraph<V, DefaultWeightedEdge> graph;

    public AlgorithmFactory(SimpleDirectedWeightedGraph<V, DefaultWeightedEdge> graph) {
        this.graph = graph;
    }

    public ShortestPathAlgorithm<V> createShortestPathAlgorithm(V startNode, V endNode) {
        return new DijkstraShortestPathDelegate<>(graph, startNode, endNode);
    }

    public CycleCountAlgorithm<V> createCycleCountAlgorithm() {
        return new TarjanSimpleCyclesDelegate<>(graph);
    }

    public CountPathsWithNumberOfStops<V, DefaultWeightedEdge> createCountPathsWithNumberOfStops(V startNode, V endNode, int maxDepth) {
        return new CountPathsWithNumberOfStops<>(graph, startNode, endNode, maxDepth);
    }

    public CountPathsWithAtLeastAGivenNumberOfStops<V, DefaultWeightedEdge> createCountPathsWithAtLeastAGivenNumberOfStops(V startNode, V endNode, int maxDepth) {
        return new CountPathsWithAtLeastAGivenNumberOfStops<>(graph, startNode, endNode, maxDepth);
    }
}
