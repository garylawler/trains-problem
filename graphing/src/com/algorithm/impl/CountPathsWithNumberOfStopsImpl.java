package com.algorithm.impl;

import com.algorithm.CountPathsWithNumberOfStops;
import org.jgrapht.DirectedGraph;

public final class CountPathsWithNumberOfStopsImpl<V, E> extends ModifiedDepthFirstTraversalTemplate<V, E> implements CountPathsWithNumberOfStops {

    private double noOfRoutesAtTargetDepth = 0;

    public CountPathsWithNumberOfStopsImpl(DirectedGraph<V, E> directedGraph, V startVertex, V endVertex, int maxDepth) {
        super(directedGraph, endVertex);
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
