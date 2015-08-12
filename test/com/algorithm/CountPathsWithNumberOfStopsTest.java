package com.algorithm;

import com.component.NamedVertex;
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

    private CountPathsWithNumberOfStops pathsWithNumberOfStops;
    private SimpleDirectedWeightedGraph<NamedVertex, DefaultWeightedEdge> graph;

    @Before
    public void onSetUp() {
        graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        graph.addVertex(NamedVertex.A);
        graph.addVertex(NamedVertex.B);
        graph.setEdgeWeight(graph.addEdge(NamedVertex.A, NamedVertex.B), 1);
        graph.setEdgeWeight(graph.addEdge(NamedVertex.B, NamedVertex.A), 2);

        pathsWithNumberOfStops = new CountPathsWithNumberOfStops<>(graph, NamedVertex.A, NamedVertex.B, 5);
    }

    @Test
    public void oneSimplePath() {
        pathsWithNumberOfStops.traverse();
        assertThat(pathsWithNumberOfStops.getNoOfRoutesAtTargetDepth(), is(1d));
    }

    @Test
    public void withAdditionalCircuit() {
        graph.addVertex(NamedVertex.C);
        graph.setEdgeWeight(graph.addEdge(NamedVertex.A, NamedVertex.C), 3);
        graph.setEdgeWeight(graph.addEdge(NamedVertex.C, NamedVertex.B), 4);

        pathsWithNumberOfStops.traverse();
        assertThat(pathsWithNumberOfStops.getNoOfRoutesAtTargetDepth(), is(2d));
    }

    @Test
    public void withAdditionalCircuitAndSpoke() {
        graph.addVertex(NamedVertex.C);
        graph.addVertex(NamedVertex.D);
        graph.setEdgeWeight(graph.addEdge(NamedVertex.A, NamedVertex.C), 3);
        graph.setEdgeWeight(graph.addEdge(NamedVertex.C, NamedVertex.D), 4);
        graph.setEdgeWeight(graph.addEdge(NamedVertex.C, NamedVertex.B), 5);

        pathsWithNumberOfStops.traverse();
        assertThat(pathsWithNumberOfStops.getNoOfRoutesAtTargetDepth(), is(2d));
    }

    @Test
    public void withAdditionalCircuitButOnlyLookingForRouteOfDepthOne() {
        pathsWithNumberOfStops = new CountPathsWithNumberOfStops<>(graph, NamedVertex.A, NamedVertex.B, 1);
        graph.addVertex(NamedVertex.C);
        graph.setEdgeWeight(graph.addEdge(NamedVertex.A, NamedVertex.C), 3);
        graph.setEdgeWeight(graph.addEdge(NamedVertex.C, NamedVertex.B), 4);

        pathsWithNumberOfStops.traverse();
        assertThat(pathsWithNumberOfStops.getNoOfRoutesAtTargetDepth(), is(1d));
    }
}
