package com.algorithm;

import com.algorithm.impl.CountPathsWithAtLeastAGivenNumberOfStopsImpl;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CountPathsWithAtLeastAGivenNumberOfStopsImplTest {

    private static final String A = "A";
    private static final String B = "B";
    private static final String C = "C";
    private static final String D = "D";
    private static final String E = "E";
    private CountPathsWithAtLeastAGivenNumberOfStopsImpl pathsWithAtLeastAGivenNumberOfStops;
    private SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph;

    @Before
    public void onSetUp() {
        graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        graph.addVertex(A);
        graph.addVertex(B);
        graph.setEdgeWeight(graph.addEdge(A, B), 1);
        graph.setEdgeWeight(graph.addEdge(B, A), 2);
    }

    @Test
    public void oneSimplePath() {
        pathsWithAtLeastAGivenNumberOfStops = new CountPathsWithAtLeastAGivenNumberOfStopsImpl<>(graph, "A", "B", 2);
        assertThat(pathsWithAtLeastAGivenNumberOfStops.getNoOfRoutes(), is(1d));
    }

    @Test
    public void withAdditionalCircuit() {
        graph.addVertex(C);
        graph.setEdgeWeight(graph.addEdge(A, C), 3);
        graph.setEdgeWeight(graph.addEdge(C, B), 4);

        pathsWithAtLeastAGivenNumberOfStops = new CountPathsWithAtLeastAGivenNumberOfStopsImpl<>(graph, "A", "B", 2);
        assertThat(pathsWithAtLeastAGivenNumberOfStops.getNoOfRoutes(), is(1d));
    }

    @Test
    public void withAdditionalCircuitSearchShouldEncompassCircuit() {
        graph.addVertex(C);
        graph.setEdgeWeight(graph.addEdge(A, C), 3);
        graph.setEdgeWeight(graph.addEdge(C, B), 4);

        pathsWithAtLeastAGivenNumberOfStops = new CountPathsWithAtLeastAGivenNumberOfStopsImpl<>(graph, "A", "B", 3);
        assertThat(pathsWithAtLeastAGivenNumberOfStops.getNoOfRoutes(), is(2d));
    }

    @Test
    public void additionalPathExceedsSearchRadius() {
        pathsWithAtLeastAGivenNumberOfStops = new CountPathsWithAtLeastAGivenNumberOfStopsImpl<>(graph, "A", "B", 3);
        graph.addVertex(C);
        graph.addVertex(D);
        graph.addVertex(E);
        graph.setEdgeWeight(graph.addEdge(A, C), 3);
        graph.setEdgeWeight(graph.addEdge(C, D), 4);
        graph.setEdgeWeight(graph.addEdge(D, E), 5);

        pathsWithAtLeastAGivenNumberOfStops = new CountPathsWithAtLeastAGivenNumberOfStopsImpl<>(graph, "A", "B", 2);
        assertThat(pathsWithAtLeastAGivenNumberOfStops.getNoOfRoutes(), is(1d));
    }
}
