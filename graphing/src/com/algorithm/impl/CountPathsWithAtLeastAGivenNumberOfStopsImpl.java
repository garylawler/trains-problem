package com.algorithm.impl;

import com.algorithm.CountPathsWithAtLeastAGivenNumberOfStops;
import org.jgrapht.DirectedGraph;

public final class CountPathsWithAtLeastAGivenNumberOfStopsImpl<V, E> extends ModifiedDepthFirstTraversalTemplate<V, E> implements CountPathsWithAtLeastAGivenNumberOfStops<V, E> {

    private double noOfRoutes = 0;

    public CountPathsWithAtLeastAGivenNumberOfStopsImpl(DirectedGraph<V, E> directedGraph, V startVertex, V endVertex, int maxDepth) {
        super(directedGraph, endVertex);
        traverseInternal(startVertex, maxDepth);
    }

    @Override
    protected void onEndVertexEncountered(int depth) {
        if(depth > 0) {
            noOfRoutes++;
        }
    }

    public double getNoOfRoutes() {
        return noOfRoutes;
    }
}
