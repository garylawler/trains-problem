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
public class CountPathsWithAtLeastAGivenNumberOfStopsTest {

    private CountPathsWithAtLeastAGivenNumberOfStops pathsWithAtLeastAGivenNumberOfStops;
    private SimpleDirectedWeightedGraph<NamedVertex, DefaultWeightedEdge> graph;

    @Before
    public void onSetUp() {
        graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        graph.addVertex(NamedVertex.A);
        graph.addVertex(NamedVertex.B);
        graph.setEdgeWeight(graph.addEdge(NamedVertex.A, NamedVertex.B), 1);
        graph.setEdgeWeight(graph.addEdge(NamedVertex.B, NamedVertex.A), 2);

        pathsWithAtLeastAGivenNumberOfStops = new CountPathsWithAtLeastAGivenNumberOfStops<>(graph, NamedVertex.A, NamedVertex.B, 2);
    }

    @Test
    public void oneSimplePath() {
        pathsWithAtLeastAGivenNumberOfStops.traverse();
        assertThat(pathsWithAtLeastAGivenNumberOfStops.getNoOfRoutes(), is(1d));
    }

    @Test
    public void withAdditionalCircuit() {
        graph.addVertex(NamedVertex.C);
        graph.setEdgeWeight(graph.addEdge(NamedVertex.A, NamedVertex.C), 3);
        graph.setEdgeWeight(graph.addEdge(NamedVertex.C, NamedVertex.B), 4);

        pathsWithAtLeastAGivenNumberOfStops.traverse();
        assertThat(pathsWithAtLeastAGivenNumberOfStops.getNoOfRoutes(), is(1d));
    }

    @Test
    public void withAdditionalCircuitSearchShouldEncompassCircuit() {
        pathsWithAtLeastAGivenNumberOfStops = new CountPathsWithAtLeastAGivenNumberOfStops<>(graph, NamedVertex.A, NamedVertex.B, 3);
        graph.addVertex(NamedVertex.C);
        graph.setEdgeWeight(graph.addEdge(NamedVertex.A, NamedVertex.C), 3);
        graph.setEdgeWeight(graph.addEdge(NamedVertex.C, NamedVertex.B), 4);

        pathsWithAtLeastAGivenNumberOfStops.traverse();
        assertThat(pathsWithAtLeastAGivenNumberOfStops.getNoOfRoutes(), is(2d));
    }

    @Test
    public void additionalPathExceedsSearchRadius() {
        pathsWithAtLeastAGivenNumberOfStops = new CountPathsWithAtLeastAGivenNumberOfStops<>(graph, NamedVertex.A, NamedVertex.B, 3);
        graph.addVertex(NamedVertex.C);
        graph.addVertex(NamedVertex.D);
        graph.addVertex(NamedVertex.E);
        graph.setEdgeWeight(graph.addEdge(NamedVertex.A, NamedVertex.C), 3);
        graph.setEdgeWeight(graph.addEdge(NamedVertex.C, NamedVertex.D), 4);
        graph.setEdgeWeight(graph.addEdge(NamedVertex.D, NamedVertex.E), 5);

        pathsWithAtLeastAGivenNumberOfStops.traverse();
        assertThat(pathsWithAtLeastAGivenNumberOfStops.getNoOfRoutes(), is(1d));
    }
}
