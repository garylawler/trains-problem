package com.algorithm;

import org.jgrapht.alg.cycle.TarjanSimpleCycles;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import java.util.List;

public final class TarjanSimpleCyclesDelegate<V> implements CycleCountAlgorithm<V> {

    private TarjanSimpleCycles<V, DefaultWeightedEdge> simpleCycles;

    public TarjanSimpleCyclesDelegate(SimpleDirectedWeightedGraph<V, DefaultWeightedEdge> graph) {
        simpleCycles = new TarjanSimpleCycles<>(graph);
    }

    @Override
    public List<List<V>> getCycles() {
        return simpleCycles.findSimpleCycles();
    }
}