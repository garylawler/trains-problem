package com.algorithm;

import org.jgrapht.DirectedGraph;

public final class CountPathsWithNumberOfStops<V, E> extends ModifiedDepthFirstTraversalTemplate<V, E> {

    private double noOfRoutesAtTargetDepth = 0;

    public CountPathsWithNumberOfStops(DirectedGraph directedGraph, V startVertex, V endVertex, int maxDepth) {
        super(directedGraph, startVertex, endVertex, maxDepth);
    }

    @Override
    protected void onEndVertexEncountered(int depth) {
        if(depth == 0){
            noOfRoutesAtTargetDepth++;
        }
    }

    public double getNoOfRoutesAtTargetDepth() {
        return noOfRoutesAtTargetDepth;
    }
}
