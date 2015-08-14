package com.algorithm.impl;

import com.algorithm.impl.DijkstraShortestPathDelegate;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.support.membermodification.MemberModifier;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DijkstraShortestPath.class)
public class DijkstraShortestPathDelegateTest {

    private static final String A = "A";
    private static final String B = "B";
    private DijkstraShortestPathDelegate<String> delegate;
    private DijkstraShortestPath<String, DefaultWeightedEdge> dijkstraShortestPath;

    @Before
    public void onSetUp() throws Exception {
        dijkstraShortestPath = PowerMockito.mock(DijkstraShortestPath.class);
        SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        graph.addVertex(A);
        graph.addVertex(B);
        delegate = new DijkstraShortestPathDelegate<>(graph, A, B);

        MemberModifier.field(DijkstraShortestPathDelegate.class, "dijkstraShortestPath")
                .set(delegate, dijkstraShortestPath);
    }

    @Test
    public void getShortestPathLength() {
        when(dijkstraShortestPath.getPathLength()).thenReturn(4d);
        assertThat(delegate.getShortestPathLength(), is(4d));
    }
}
