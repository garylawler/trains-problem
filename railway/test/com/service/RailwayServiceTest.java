package com.service;

import com.model.Station;
import com.exception.PathNotFoundException;
import com.facade.GraphFacade;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RailwayServiceTest {

    private RailwayService railwayService;
    private @Mock GraphFacade<Station> graphFacade;

    @Before
    public void onSetUp() {
        railwayService = new RailwayService(graphFacade);
    }

    @Test
    public void getRouteLengthTwoStations() {
        when(graphFacade.getEdgeWeight(Station.A, Station.B)).thenReturn(4d);
        assertThat(railwayService.getRouteLength(Station.A, Station.B), is(4d));
    }

    @Test
    public void getRouteLengthThreeStations() {
        when(graphFacade.getEdgeWeight(any(Station.class), any(Station.class))).thenReturn(4d);
        assertThat(railwayService.getRouteLength(Station.A, Station.B, Station.C), is(8d));
    }

    @Test(expected = PathNotFoundException.class)
    public void getRouteLengthInvalidRoute() {
        when(graphFacade.getEdgeWeight(Station.A, Station.B)).thenThrow(new PathNotFoundException(Station.A, Station.B));
        railwayService.getRouteLength(Station.A, Station.B);
    }

    @Test
    public void getShortestNonLoopingRouteLength() {
        when(graphFacade.getShortestAcyclicPathLength(Station.A, Station.B)).thenReturn(4d);
        assertThat(railwayService.getShortestNonLoopingRouteLength(Station.A, Station.B), is(4d));
    }

    public void getPathsWithStops() {
        when(graphFacade.getPathsWithStops(Station.A, Station.B, 5)).thenReturn(4d);
        assertThat(railwayService.getPathsWithStops(Station.A, Station.B, 5), is(4d));
    }

    public double getNumberOfRoutesWithExactNumberOfStops() {
        when(graphFacade.getPathsWithExactNodes(Station.A, Station.B, 5)).thenReturn(4d);
        return railwayService.getNumberOfRoutesWithExactNumberOfStops(Station.A, Station.B, 5);
    }
//
//    public double getShortestLoopLengthIncludingGivenStation(Station vertex) {
//        double cycleLength = Double.MAX_VALUE;
//        for(List<Station> nodeList : railway.getCycles()) {
//            if(nodeList.contains(vertex)) {
//                double latestCycleLength = getCycleLength(nodeList);
//                cycleLength = latestCycleLength < cycleLength ? latestCycleLength : cycleLength;
//            }
//        }
//        return cycleLength;
//    }
}
