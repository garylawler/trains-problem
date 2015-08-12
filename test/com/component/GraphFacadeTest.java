package com.component;

import com.algorithm.CountPathsWithNumberOfStops;
import com.algorithm.CountPathsWithAtLeastAGivenNumberOfStops;
import com.factory.AlgorithmFactory;
import com.exception.PathNotFoundException;
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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DijkstraShortestPath.class, TarjanSimpleCycles.class, CountPathsWithAtLeastAGivenNumberOfStops.class, CountPathsWithNumberOfStops.class})
public class GraphFacadeTest {

    private GraphFacade graphFacade;
    private @Mock SimpleDirectedWeightedGraph<NamedVertex, DefaultWeightedEdge> simpleDirectedWeightedGraph;
    private @Mock AlgorithmFactory algorithmFactory;
    private @Mock DefaultWeightedEdge defaultWeightedEdge;
    private DijkstraShortestPath dijkstraShortestPath;
    private TarjanSimpleCycles tarjanSimpleCycles;
    private CountPathsWithAtLeastAGivenNumberOfStops maximumStops;
    private CountPathsWithNumberOfStops exactStops;

    @Before
    public void onSetUp() {
        dijkstraShortestPath = PowerMockito.mock(DijkstraShortestPath.class);
        tarjanSimpleCycles = PowerMockito.mock(TarjanSimpleCycles.class);
        maximumStops = PowerMockito.mock(CountPathsWithAtLeastAGivenNumberOfStops.class);
        exactStops = PowerMockito.mock(CountPathsWithNumberOfStops.class);

        graphFacade = new GraphFacade(simpleDirectedWeightedGraph, algorithmFactory);
        when(algorithmFactory.createDijkstraShortestPath(any(NamedVertex.class), any(NamedVertex.class))).thenReturn(dijkstraShortestPath);
        when(algorithmFactory.createTarjanSimpleCycles()).thenReturn(tarjanSimpleCycles);
        when(algorithmFactory.createCountPathsWithAtLeastAGivenNumberOfStops(any(NamedVertex.class), any(NamedVertex.class), anyInt())).thenReturn(maximumStops);
        when(algorithmFactory.createCountPathsWithNumberOfStops(any(NamedVertex.class), any(NamedVertex.class), anyInt())).thenReturn(exactStops);
    }

    @Test
    public void addEdgeAllVerticesPresent() {
        when(simpleDirectedWeightedGraph.containsVertex(any(NamedVertex.class))).thenReturn(true);
        when(simpleDirectedWeightedGraph.addEdge(any(NamedVertex.class), any(NamedVertex.class))).thenReturn(defaultWeightedEdge);
        graphFacade.addEdge(NamedVertex.A, NamedVertex.B, 5);
        verify(simpleDirectedWeightedGraph, never()).addVertex(NamedVertex.A);
        verify(simpleDirectedWeightedGraph, never()).addVertex(NamedVertex.B);
        verify(simpleDirectedWeightedGraph, times(1)).addEdge(NamedVertex.A, NamedVertex.B);
        verify(simpleDirectedWeightedGraph, times(1)).setEdgeWeight(defaultWeightedEdge, 5);
    }

    @Test
    public void addEdgeNoVerticesPresent() {
        when(simpleDirectedWeightedGraph.containsVertex(any(NamedVertex.class))).thenReturn(false);
        when(simpleDirectedWeightedGraph.addEdge(any(NamedVertex.class), any(NamedVertex.class))).thenReturn(defaultWeightedEdge);
        graphFacade.addEdge(NamedVertex.A, NamedVertex.B, 5);
        verify(simpleDirectedWeightedGraph, times(1)).addVertex(NamedVertex.A);
        verify(simpleDirectedWeightedGraph, times(1)).addVertex(NamedVertex.B);
        verify(simpleDirectedWeightedGraph, times(1)).addEdge(NamedVertex.A, NamedVertex.B);
        verify(simpleDirectedWeightedGraph, times(1)).setEdgeWeight(defaultWeightedEdge, 5);
    }

    @Test
    public void getEdgeWeightEdgeExists() {
        when(simpleDirectedWeightedGraph.getEdge(NamedVertex.A, NamedVertex.B)).thenReturn(defaultWeightedEdge);
        when(simpleDirectedWeightedGraph.getEdgeWeight(defaultWeightedEdge)).thenReturn(5d);
        assertThat(graphFacade.getEdgeWeight(NamedVertex.A, NamedVertex.B), is(5d));
    }

    @Test(expected = PathNotFoundException.class)
    public void getEdgeWeightEdgeDoesNotExist() {
        when(simpleDirectedWeightedGraph.getEdge(NamedVertex.A, NamedVertex.B)).thenReturn(null);
        assertThat(graphFacade.getEdgeWeight(NamedVertex.A, NamedVertex.B), is(5d));
        verify(simpleDirectedWeightedGraph, never()).getEdgeWeight(defaultWeightedEdge);
    }

    @Test
    public void getShortestAcyclicPathLength() {
        double expectedLength = 3d;
        when(dijkstraShortestPath.getPathLength()).thenReturn(expectedLength);
        assertThat(graphFacade.getShortestAcyclicPathLength(NamedVertex.A, NamedVertex.B), is(expectedLength));
        verify(algorithmFactory, times(1)).createDijkstraShortestPath(NamedVertex.A, NamedVertex.B);
    }

    @Test
    public void getPathsWithStops() {
        double expectedNumberOfStops = 5;
        when(maximumStops.getNoOfRoutes()).thenReturn(expectedNumberOfStops);
        assertThat(graphFacade.getPathsWithStops(NamedVertex.A, NamedVertex.B, 5), is(expectedNumberOfStops));
        verify(algorithmFactory, times(1)).createCountPathsWithAtLeastAGivenNumberOfStops(NamedVertex.A, NamedVertex.B, 5);
    }

    public void getPathsWithExactNodes() {
        double expectedNumberOfStops = 5;
        when(exactStops.getNoOfRoutesAtTargetDepth()).thenReturn(expectedNumberOfStops);
        assertThat(graphFacade.getPathsWithExactNodes(NamedVertex.A, NamedVertex.B, 5), is(expectedNumberOfStops));
        verify(algorithmFactory, times(1)).createCountPathsWithNumberOfStops(NamedVertex.A, NamedVertex.B, 5);
    }
//
//    public List<NamedVertex> getCycleContainingNode(String node) {
//        for(List<NamedVertex> nodeList : getCycles()) {
//            if(nodeList.contains(node)) {
//                return nodeList;
//            }
//        }
//        return new ArrayList<>();
//    }
////
//    public double getCycleLength(List<NamedVertex> nodes) {
//        int lengthOfCycle = 0;
//        int numberOfNodes = nodes.size();
//        if(numberOfNodes > 1) {
//            for(int i=1; i< numberOfNodes; i++) {
//                lengthOfCycle += getEdgeWeight(nodes.get(i-1), nodes.get(i));
//            }
//            lengthOfCycle += getEdgeWeight(nodes.get(numberOfNodes-1), nodes.get(0));
//        }
//        return lengthOfCycle;
//
//    }
//
//    public List<List<NamedVertex>> getCycles() {
//        TarjanSimpleCycles<NamedVertex, DefaultWeightedEdge> simpleCycles = algorithmFactory.createTarjanSimpleCycles();
//        return simpleCycles.findSimpleCycles();
//    }
//



}
