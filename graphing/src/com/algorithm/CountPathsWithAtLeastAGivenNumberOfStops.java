package com.algorithm;

import org.jgrapht.DirectedGraph;

public final class CountPathsWithAtLeastAGivenNumberOfStops<V, E> extends ModifiedDepthFirstTraversalTemplate<V, E> {

    private double noOfRoutes = 0;

    public CountPathsWithAtLeastAGivenNumberOfStops(DirectedGraph directedGraph, V startVertex, V endVertex, int maxDepth) {
        super(directedGraph, startVertex, endVertex, maxDepth);
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
