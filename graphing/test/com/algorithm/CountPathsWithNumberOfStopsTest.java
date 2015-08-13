package com.algorithm;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CountPathsWithNumberOfStopsTest {

    private static final String A = "A";
    private static final String B = "B";
    private static final String C = "C";
    private static final String D = "D";
    private CountPathsWithNumberOfStops pathsWithNumberOfStops;
    private SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph;

    @Before
    public void onSetUp() {
        graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        graph.addVertex(A);
        graph.addVertex(B);
        graph.setEdgeWeight(graph.addEdge(A, B), 1);
        graph.setEdgeWeight(graph.addEdge(B, A), 2);

        pathsWithNumberOfStops = new CountPathsWithNumberOfStops<>(graph, A, B, 5);
    }

    @Test
    public void oneSimplePath() {
        pathsWithNumberOfStops.traverse();
        assertThat(pathsWithNumberOfStops.getNoOfRoutesAtTargetDepth(), is(1d));
    }

    @Test
    public void withAdditionalCircuit() {
        graph.addVertex(C);
        graph.setEdgeWeight(graph.addEdge(A, C), 3);
        graph.setEdgeWeight(graph.addEdge(C, B), 4);

        pathsWithNumberOfStops.traverse();
        assertThat(pathsWithNumberOfStops.getNoOfRoutesAtTargetDepth(), is(2d));
    }

    @Test
    public void withAdditionalCircuitAndSpoke() {
        graph.addVertex(C);
        graph.addVertex(D);
        graph.setEdgeWeight(graph.addEdge(A, C), 3);
        graph.setEdgeWeight(graph.addEdge(C, D), 4);
        graph.setEdgeWeight(graph.addEdge(C, B), 5);

        pathsWithNumberOfStops.traverse();
        assertThat(pathsWithNumberOfStops.getNoOfRoutesAtTargetDepth(), is(2d));
    }

    @Test
    public void withAdditionalCircuitButOnlyLookingForRouteOfDepthOne() {
        pathsWithNumberOfStops = new CountPathsWithNumberOfStops<>(graph, A, B, 1);
        graph.addVertex(C);
        graph.setEdgeWeight(graph.addEdge(A, C), 3);
        graph.setEdgeWeight(graph.addEdge(C, B), 4);

        pathsWithNumberOfStops.traverse();
        assertThat(pathsWithNumberOfStops.getNoOfRoutesAtTargetDepth(), is(1d));
    }
}
