package com.algorithm;

import org.jgrapht.DirectedGraph;

import java.util.Set;

abstract class ModifiedDepthFirstTraversalTemplate<V, E> {

    private int maxDepth = 0;
    private V startDepth;
    private V endVertex;
    private DirectedGraph<V,E> directedGraph;

    public ModifiedDepthFirstTraversalTemplate(DirectedGraph<V, E> directedGraph, V startDepth, V endVertex, int maxDepth) {
        this.startDepth = startDepth;
        this.endVertex = endVertex;
        this.maxDepth = maxDepth;
        this.directedGraph = directedGraph;
    }

    public boolean traverse(){
        return traverseInternal(startDepth, maxDepth);
    }

    private boolean traverseInternal(V v, int depth){
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
