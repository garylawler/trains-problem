package com.algorithm.impl;

import com.algorithm.ShortestPath;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

final class DijkstraShortestPathDelegate<V> implements ShortestPath<V> {

    private DijkstraShortestPath dijkstraShortestPath;

    public DijkstraShortestPathDelegate(SimpleDirectedWeightedGraph<V, DefaultWeightedEdge> graph, V startNode, V endNode) {
        dijkstraShortestPath = new DijkstraShortestPath<>(graph, startNode, endNode);
    }

    public double getShortestPathLength() {
        return dijkstraShortestPath.getPathLength();
    }
}
