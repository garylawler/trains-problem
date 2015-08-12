package com.factory;

import com.algorithm.CountPathsWithNumberOfStops;
import com.algorithm.CountPathsWithAtLeastAGivenNumberOfStops;
import com.component.NamedVertex;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.alg.cycle.TarjanSimpleCycles;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class AlgorithmFactory {

    private SimpleDirectedWeightedGraph<NamedVertex, DefaultWeightedEdge> graph;

    public AlgorithmFactory(SimpleDirectedWeightedGraph<NamedVertex, DefaultWeightedEdge> graph) {
        this.graph = graph;
    }

    public DijkstraShortestPath<NamedVertex, DefaultWeightedEdge> createDijkstraShortestPath(NamedVertex startNode, NamedVertex endNode) {
        return new DijkstraShortestPath<>(graph, startNode, endNode);
    }

    public TarjanSimpleCycles<NamedVertex, DefaultWeightedEdge> createTarjanSimpleCycles() {
        return new TarjanSimpleCycles<>(graph);
    }

    public CountPathsWithNumberOfStops<NamedVertex, DefaultWeightedEdge> createCountPathsWithNumberOfStops(NamedVertex startNode, NamedVertex endNode, int maxDepth) {
        return new CountPathsWithNumberOfStops<>(graph, startNode, endNode, maxDepth);
    }

    public CountPathsWithAtLeastAGivenNumberOfStops<NamedVertex, DefaultWeightedEdge> createCountPathsWithAtLeastAGivenNumberOfStops(NamedVertex startNode, NamedVertex endNode, int maxDepth) {
        return new CountPathsWithAtLeastAGivenNumberOfStops<>(graph, startNode, endNode, maxDepth);
    }
}
