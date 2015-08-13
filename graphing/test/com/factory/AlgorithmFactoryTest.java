package com.factory;

import com.algorithm.CountPathsWithAtLeastAGivenNumberOfStops;
import com.algorithm.CountPathsWithNumberOfStops;
import com.algorithm.CycleCountAlgorithm;
import com.algorithm.ShortestPathAlgorithm;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.MatcherAssert.assertThat;

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
        assertThat(algorithmFactory.createShortestPathAlgorithm(A, B), isA(ShortestPathAlgorithm.class));
    }

    @Test
    public void createCycleCountAlgorithm() {
        assertThat(algorithmFactory.createCycleCountAlgorithm(), isA(CycleCountAlgorithm.class));
    }

    @Test
    public void createDepthFirstPathCounterExactStops() {
        assertThat(algorithmFactory.createCountPathsWithNumberOfStops(A, B, 5), isA(CountPathsWithNumberOfStops.class));
    }

    @Test
    public void createDepthFirstPathCounterMaximumStops() {
        assertThat(algorithmFactory.createCountPathsWithAtLeastAGivenNumberOfStops(A, B, 5), isA(CountPathsWithAtLeastAGivenNumberOfStops.class));
    }
}
