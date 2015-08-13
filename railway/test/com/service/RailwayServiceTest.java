package com.service;

import com.component.GraphFacade;
import org.junit.Before;
import org.mockito.Mock;

public class RailwayServiceTest {

    private RailwayService railwayService;
    private @Mock GraphFacade graphFacade;

    @Before
    public void onSetUp() {
        railwayService = new RailwayService(graphFacade);
    }
}
