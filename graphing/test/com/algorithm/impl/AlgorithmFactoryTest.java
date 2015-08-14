package com.algorithm.impl;

import com.algorithm.CountPathsWithAtLeastAGivenNumberOfStops;
import com.algorithm.CountPathsWithNumberOfStops;
import com.algorithm.CycleCount;
import com.algorithm.ShortestPath;
import org.hamcrest.MatcherAssert;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.isA;

@RunWith(MockitoJUnitRunner.class)
public class AlgorithmFactoryTest {

    private static final String A = "A";
    private static final String B = "B";
    private AlgorithmFactory<String> algorithmFactory;

    @Before
    public void onSetUp() {
        SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        graph.addVertex(A);
        graph.addVertex(B);
        algorithmFactory = new AlgorithmFactory<>(graph);
    }

    @Test
    public void createShortestPathAlgorithm() {
        MatcherAssert.assertThat(algorithmFactory.createShortestPathAlgorithm(A, B), isA(ShortestPath.class));
    }

    @Test
    public void createCycleCountAlgorithm() {
        MatcherAssert.assertThat(algorithmFactory.createCycleCountAlgorithm(), isA(CycleCount.class));
    }

    @Test
    public void createDepthFirstPathCounterExactStops() {
        MatcherAssert.assertThat(algorithmFactory.createCountPathsWithNumberOfStops(A, B, 5), isA(CountPathsWithNumberOfStops.class));
    }

    @Test
    public void createDepthFirstPathCounterMaximumStops() {
        MatcherAssert.assertThat(algorithmFactory.createCountPathsWithAtLeastAGivenNumberOfStops(A, B, 5), isA(CountPathsWithAtLeastAGivenNumberOfStops.class));
    }
}
