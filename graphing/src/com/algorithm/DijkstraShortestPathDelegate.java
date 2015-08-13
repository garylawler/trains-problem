package com.algorithm;

import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public final class DijkstraShortestPathDelegate<V> implements ShortestPathAlgorithm<V> {

    private DijkstraShortestPath dijkstraShortestPath;

    public DijkstraShortestPathDelegate(SimpleDirectedWeightedGraph<V, DefaultWeightedEdge> graph, V startNode, V endNode) {
        dijkstraShortestPath = new DijkstraShortestPath<>(graph, startNode, endNode);
    }

    public double getShortestPathLength() {
        return dijkstraShortestPath.getPathLength();
    }
}
