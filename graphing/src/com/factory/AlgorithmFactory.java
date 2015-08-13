package com.factory;

import com.algorithm.CountPathsWithAtLeastAGivenNumberOfStops;
import com.algorithm.CountPathsWithNumberOfStops;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.alg.cycle.TarjanSimpleCycles;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class AlgorithmFactory<V> {

    private SimpleDirectedWeightedGraph<V, DefaultWeightedEdge> graph;

    public AlgorithmFactory(SimpleDirectedWeightedGraph<V, DefaultWeightedEdge> graph) {
        this.graph = graph;
    }

    public DijkstraShortestPath<V, DefaultWeightedEdge> createDijkstraShortestPath(V startNode, V endNode) {
        return new DijkstraShortestPath<>(graph, startNode, endNode);
    }

    public TarjanSimpleCycles<V, DefaultWeightedEdge> createTarjanSimpleCycles() {
        return new TarjanSimpleCycles<>(graph);
    }

    public CountPathsWithNumberOfStops<V, DefaultWeightedEdge> createCountPathsWithNumberOfStops(V startNode, V endNode, int maxDepth) {
        return new CountPathsWithNumberOfStops<>(graph, startNode, endNode, maxDepth);
    }

    public CountPathsWithAtLeastAGivenNumberOfStops<V, DefaultWeightedEdge> createCountPathsWithAtLeastAGivenNumberOfStops(V startNode, V endNode, int maxDepth) {
        return new CountPathsWithAtLeastAGivenNumberOfStops<>(graph, startNode, endNode, maxDepth);
    }
}
