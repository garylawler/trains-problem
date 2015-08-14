package com.algorithm.impl;

import com.algorithm.ShortestPath;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

final class DijkstraShortestPathDelegate<V> implements ShortestPath<V> {

    private DijkstraShortestPath dijkstraShortestPath;

    public DijkstraShortestPathDelegate(SimpleDirectedWeightedGraph<V, DefaultWeightedEdge> graph, V startVertex, V endVertex) {
        dijkstraShortestPath = new DijkstraShortestPath<>(graph, startVertex, endVertex);
    }

    public double getShortestPathLength() {
        return dijkstraShortestPath.getPathLength();
    }
}
