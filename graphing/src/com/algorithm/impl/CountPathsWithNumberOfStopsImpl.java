package com.algorithm.impl;

import com.algorithm.CountPathsWithNumberOfStops;
import org.jgrapht.DirectedGraph;

public final class CountPathsWithNumberOfStopsImpl<V, E> extends ModifiedDepthFirstTraversalTemplate<V, E> implements CountPathsWithNumberOfStops {

    private double noOfRoutesAtTargetDepth = 0;

    public CountPathsWithNumberOfStopsImpl(DirectedGraph directedGraph, V startVertex, V endVertex, int maxDepth) {
        super(directedGraph, startVertex, endVertex, maxDepth);
        traverseInternal(startVertex, maxDepth);
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
