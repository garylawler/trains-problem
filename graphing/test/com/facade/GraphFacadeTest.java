package com.facade;

import com.algorithm.impl.CountPathsWithAtLeastAGivenNumberOfStopsImpl;
import com.algorithm.impl.CountPathsWithNumberOfStopsImpl;
import com.algorithm.CycleCount;
import com.algorithm.ShortestPath;
import com.exception.PathNotFoundException;
import com.algorithm.impl.AlgorithmFactory;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.alg.cycle.TarjanSimpleCycles;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DijkstraShortestPath.class, TarjanSimpleCycles.class, CountPathsWithAtLeastAGivenNumberOfStopsImpl.class, CountPathsWithNumberOfStopsImpl.class})
public class GraphFacadeTest {

    private static final String A = "A";
    private static final String B = "B";
    private GraphFacade<String> graphFacade;
    private @Mock SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> simpleDirectedWeightedGraph;
    private @Mock AlgorithmFactory<String> algorithmFactory;
    private @Mock DefaultWeightedEdge defaultWeightedEdge;
    private @Mock
    ShortestPath<String> shortestPath;
    private @Mock
    CycleCount<String> cycleCount;
    private CountPathsWithAtLeastAGivenNumberOfStopsImpl maximumStops;
    private CountPathsWithNumberOfStopsImpl exactStops;

    @Before
    public void onSetUp() {
        maximumStops = PowerMockito.mock(CountPathsWithAtLeastAGivenNumberOfStopsImpl.class);
        exactStops = PowerMockito.mock(CountPathsWithNumberOfStopsImpl.class);

        graphFacade = new GraphFacade<>(simpleDirectedWeightedGraph, algorithmFactory);
        when(algorithmFactory.createShortestPathAlgorithm(anyString(), anyString())).thenReturn(shortestPath);
        when(algorithmFactory.createCycleCountAlgorithm()).thenReturn(cycleCount);
        when(algorithmFactory.createCountPathsWithAtLeastAGivenNumberOfStops(anyString(), anyString(), anyInt())).thenReturn(maximumStops);
        when(algorithmFactory.createCountPathsWithNumberOfStops(anyString(), anyString(), anyInt())).thenReturn(exactStops);
    }

    @Test
    public void addEdgeAllVerticesPresent() {
        when(simpleDirectedWeightedGraph.containsVertex(anyString())).thenReturn(true);
        when(simpleDirectedWeightedGraph.addEdge(anyString(), anyString())).thenReturn(defaultWeightedEdge);
        graphFacade.addEdge(A, B, 5);
        verify(simpleDirectedWeightedGraph, never()).addVertex(A);
        verify(simpleDirectedWeightedGraph, never()).addVertex(B);
        verify(simpleDirectedWeightedGraph, times(1)).addEdge(A, B);
        verify(simpleDirectedWeightedGraph, times(1)).setEdgeWeight(defaultWeightedEdge, 5);
    }

    @Test
    public void addEdgeNoVerticesPresent() {
        when(simpleDirectedWeightedGraph.containsVertex(anyString())).thenReturn(false);
        when(simpleDirectedWeightedGraph.addEdge(anyString(), anyString())).thenReturn(defaultWeightedEdge);
        graphFacade.addEdge(A, B, 5);
        verify(simpleDirectedWeightedGraph, times(1)).addVertex(A);
        verify(simpleDirectedWeightedGraph, times(1)).addVertex(B);
        verify(simpleDirectedWeightedGraph, times(1)).addEdge(A, B);
        verify(simpleDirectedWeightedGraph, times(1)).setEdgeWeight(defaultWeightedEdge, 5);
    }

    @Test
    public void getEdgeWeightEdgeExists() {
        when(simpleDirectedWeightedGraph.getEdge(A, B)).thenReturn(defaultWeightedEdge);
        when(simpleDirectedWeightedGraph.getEdgeWeight(defaultWeightedEdge)).thenReturn(5d);
        assertThat(graphFacade.getEdgeWeight(A, B), is(5d));
    }

    @Test(expected = PathNotFoundException.class)
    public void getEdgeWeightEdgeDoesNotExist() {
        when(simpleDirectedWeightedGraph.getEdge(A, B)).thenReturn(null);
        assertThat(graphFacade.getEdgeWeight(A, B), is(5d));
        verify(simpleDirectedWeightedGraph, never()).getEdgeWeight(defaultWeightedEdge);
    }

    @Test
    public void getShortestAcyclicPathLength() {
        double expectedLength = 3d;
        when(shortestPath.getShortestPathLength()).thenReturn(expectedLength);
        assertThat(graphFacade.getShortestAcyclicPathLength(A, B), is(expectedLength));
        verify(algorithmFactory, times(1)).createShortestPathAlgorithm(A, B);
    }

    @Test
    public void getPathsWithStops() {
        double expectedNumberOfStops = 5;
        when(maximumStops.getNoOfRoutes()).thenReturn(expectedNumberOfStops);
        assertThat(graphFacade.countPathsWithStops(A, B, 5), is(expectedNumberOfStops));
        verify(algorithmFactory, times(1)).createCountPathsWithAtLeastAGivenNumberOfStops(A, B, 5);
    }

    @Test
    public void getPathsWithExactVertexs() {
        double expectedNumberOfStops = 5;
        when(exactStops.getNoOfRoutesAtTargetDepth()).thenReturn(expectedNumberOfStops);
        assertThat(graphFacade.countPathsWithExactVertexs(A, B, 5), is(expectedNumberOfStops));
        verify(algorithmFactory, times(1)).createCountPathsWithNumberOfStops(A, B, 5);
    }

    @Test
    public void getCycles() {
        List<List<String>> expected = new ArrayList<>();
        when(cycleCount.getCycles()).thenReturn(expected);
        assertThat(graphFacade.getCycles(), is(expected));
        verify(algorithmFactory, times(1)).createCycleCountAlgorithm();
    }
}
