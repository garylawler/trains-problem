package com.algorithm.impl;

import org.jgrapht.alg.cycle.TarjanSimpleCycles;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.support.membermodification.MemberModifier;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(TarjanSimpleCycles.class)
public class CycleCountDelegateTest {

    private static final String A = "A";
    private static final String B = "B";
    private TarjanSimpleCyclesDelegate<String> delegate;
    private TarjanSimpleCycles<String, DefaultWeightedEdge> tarjanSimpleCycles;

    @Before
    public void onSetUp() throws Exception {
        tarjanSimpleCycles = PowerMockito.mock(TarjanSimpleCycles.class);
        SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        graph.addVertex(A);
        graph.addVertex(B);
        delegate = new TarjanSimpleCyclesDelegate<>(graph);

        MemberModifier.field(TarjanSimpleCyclesDelegate.class, "simpleCycles")
                .set(delegate, tarjanSimpleCycles);
    }

    @Test
    public void getCycles() {
        List<List<String>> cycles = new ArrayList<>();
        when(tarjanSimpleCycles.findSimpleCycles()).thenReturn(cycles);
        assertThat(delegate.getCycles(), is(cycles));
    }
}
