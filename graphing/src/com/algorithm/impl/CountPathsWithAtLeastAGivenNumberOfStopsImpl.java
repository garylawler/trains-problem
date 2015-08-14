package com.algorithm.impl;

import com.algorithm.CountPathsWithAtLeastAGivenNumberOfStops;
import org.jgrapht.DirectedGraph;

public final class CountPathsWithAtLeastAGivenNumberOfStopsImpl<V, E> extends ModifiedDepthFirstTraversalTemplate<V, E> implements CountPathsWithAtLeastAGivenNumberOfStops {

    private double noOfRoutes = 0;

    public CountPathsWithAtLeastAGivenNumberOfStopsImpl(DirectedGraph directedGraph, V startVertex, V endVertex, int maxDepth) {
        super(directedGraph, startVertex, endVertex, maxDepth);
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
