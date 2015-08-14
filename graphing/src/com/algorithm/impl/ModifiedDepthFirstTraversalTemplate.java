package com.algorithm.impl;

import org.jgrapht.DirectedGraph;

import java.util.Set;

abstract class ModifiedDepthFirstTraversalTemplate<V, E> {

    private int maxDepth = 0;
    private V startVertex;
    private V endVertex;
    private DirectedGraph<V,E> directedGraph;

    public ModifiedDepthFirstTraversalTemplate(DirectedGraph<V, E> directedGraph, V startVertex, V endVertex, int maxDepth) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.maxDepth = maxDepth;
        this.directedGraph = directedGraph;
    }

    protected boolean traverseInternal(V v, int depth){
        if(v == endVertex) {
            onEndVertexEncountered(depth);
        }

        if (depth > 0) {
            Set<E> outboundEdges = directedGraph.outgoingEdgesOf(v);
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
