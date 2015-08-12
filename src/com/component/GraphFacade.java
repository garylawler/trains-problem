package com.component;

import com.algorithm.CountPathsWithAtLeastAGivenNumberOfStops;
import com.algorithm.CountPathsWithNumberOfStops;
import com.exception.PathNotFoundException;
import com.factory.AlgorithmFactory;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class GraphFacade {
    private SimpleDirectedWeightedGraph<NamedVertex, DefaultWeightedEdge> directedGraph;
    private AlgorithmFactory algorithmFactory;

    public GraphFacade(SimpleDirectedWeightedGraph<NamedVertex, DefaultWeightedEdge> directedGraph, AlgorithmFactory algorithmFactory) {
        this.directedGraph = directedGraph;
        this.algorithmFactory = algorithmFactory;
    }

    public double getEdgeWeight(NamedVertex startNode, NamedVertex endNode) throws PathNotFoundException{
        DefaultWeightedEdge selectedEdge = directedGraph.getEdge(startNode, endNode);
        if(selectedEdge != null) {
            return directedGraph.getEdgeWeight(selectedEdge);
        }
        throw new PathNotFoundException(startNode, endNode);
    }

    public void addEdge(NamedVertex startNode, NamedVertex endNode, int weight) {
        addVertex(startNode);
        addVertex(endNode);
        directedGraph.setEdgeWeight(directedGraph.addEdge(startNode, endNode), weight);
    }

    public double getShortestAcyclicPathLength(NamedVertex startNode, NamedVertex endNode) {
        DijkstraShortestPath<NamedVertex, DefaultWeightedEdge> dijkstraShortestPath = algorithmFactory.createDijkstraShortestPath(startNode, endNode);
        return dijkstraShortestPath.getPathLength();
    }

    public double getPathsWithStops(NamedVertex startNode, NamedVertex endNode, int numberOfStops ) {
        CountPathsWithAtLeastAGivenNumberOfStops<NamedVertex, DefaultWeightedEdge> depthFirstPathCounter2 = algorithmFactory.createCountPathsWithAtLeastAGivenNumberOfStops(startNode, endNode, numberOfStops);
        depthFirstPathCounter2.traverse();
        return depthFirstPathCounter2.getNoOfRoutes();
    }

    public double getPathsWithExactNodes(NamedVertex startNode, NamedVertex endNode, int numberOfStops ) {
        CountPathsWithNumberOfStops<NamedVertex, DefaultWeightedEdge> depthFirstPathCounter = algorithmFactory.createCountPathsWithNumberOfStops(startNode, endNode, numberOfStops);
        depthFirstPathCounter.traverse();
        return depthFirstPathCounter.getNoOfRoutesAtTargetDepth();
    }

    private void addVertex(NamedVertex vertex) {
        if(!directedGraph.containsVertex(vertex)) {
            directedGraph.addVertex(vertex);
        }
    }
}
