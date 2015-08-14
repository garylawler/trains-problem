package com.algorithm.impl;

import org.jgrapht.DirectedGraph;

import java.util.Set;

abstract class ModifiedDepthFirstTraversalTemplate<V, E> {

    private V endVertex;
    private DirectedGraph<V,E> directedGraph;

    public ModifiedDepthFirstTraversalTemplate(DirectedGraph<V, E> directedGraph, V endVertex) {
        this.endVertex = endVertex;
        this.directedGraph = directedGraph;
    }

    protected boolean traverseInternal(V originVertex, int depth){
        if(originVertex == endVertex) {
            onEndVertexEncountered(depth);
        }

        if (depth > 0) {
            Set<E> outboundEdges = directedGraph.outgoingEdgesOf(originVertex);
            for(E edge : outboundEdges) {
                V targetVertex = directedGraph.getEdgeTarget(edge);
                if(traverseInternal(targetVertex, depth - 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected abstract void onEndVertexEncountered(int depth);
}
