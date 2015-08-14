package com.service;

import com.exception.PathNotFoundException;
import com.facade.GraphFacade;
import com.model.Station;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Test
    public void getPathsWithStops() {
        when(graphFacade.countPathsWithStops(Station.A, Station.B, 5)).thenReturn(4d);
        assertThat(railwayService.getNumberOfRoutesWithStops(Station.A, Station.B, 5), is(4d));
    }

    @Test
    public void getNumberOfRoutesWithExactNumberOfStops() {
        when(graphFacade.countPathsWithExactVertexs(Station.A, Station.B, 5)).thenReturn(2d);
        assertThat(railwayService.getNumberOfRoutesWithExactNumberOfStops(Station.A, Station.B, 5), is(2d));
    }

    @Test
    public void getShortestLoopLengthIncludingGivenStation() {
        List<Station> stationListIncludingA = new ArrayList<>(Arrays.asList(Station.A, Station.B, Station.C));
        List<Station> stationListExcludingA = new ArrayList<>(Arrays.asList(Station.B, Station.C, Station.D));
        when(graphFacade.getCycles()).thenReturn(Arrays.asList(stationListIncludingA, stationListExcludingA));
        when(graphFacade.getEdgeWeight(any(Station.class), any(Station.class))).thenReturn(4d);

        // One loop a->b->c has a cost of 12. Therefore there are two valid loops with a cost less than 30:
        // a->b->c and a->b->c->a->b->c
        assertThat(railwayService.getNumberOfRoutesIncludingStationWithDistanceLessThanThirty(Station.A), is(2d));
    }

    @Test
    public void getShortestLoopLengthIncludingGivenStationNoSuchRoute() {
        List<Station> stationListIncludingA = new ArrayList<>(Arrays.asList(Station.A, Station.B, Station.C));
        List<Station> stationListExcludingA = new ArrayList<>(Arrays.asList(Station.B, Station.C, Station.D));
        when(graphFacade.getCycles()).thenReturn(Arrays.asList(stationListIncludingA, stationListExcludingA));
        when(graphFacade.getEdgeWeight(any(Station.class), any(Station.class))).thenReturn(10d);

        assertThat(railwayService.getNumberOfRoutesIncludingStationWithDistanceLessThanThirty(Station.A), is(0d));
    }
}
