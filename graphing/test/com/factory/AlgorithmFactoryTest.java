package com.factory;

import com.algorithm.CountPathsWithAtLeastAGivenNumberOfStops;
import com.algorithm.CountPathsWithNumberOfStops;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.alg.cycle.TarjanSimpleCycles;
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
    private AlgorithmFactory algorithmFactory;
    private SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph;

    @Before
    public void onSetUp() {
        graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        graph.addVertex(A);
        graph.addVertex(B);
        algorithmFactory = new AlgorithmFactory<>(graph);
    }

    @Test
    public void createDijkstraShortestPath() {
        assertThat(algorithmFactory.createDijkstraShortestPath(A, B), isA(DijkstraShortestPath.class));
    }

    @Test
    public void createTarjanSimpleCycles() {
        assertThat(algorithmFactory.createTarjanSimpleCycles(), isA(TarjanSimpleCycles.class));
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
