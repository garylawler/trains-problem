package com.config;

import com.model.Station;
import com.facade.GraphFacade;
import com.algorithm.impl.AlgorithmFactory;
import com.service.RailwayService;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class AppConfig {

    @Bean
    @Autowired
    RailwayService getRailwayService(GraphFacade graphFacade) {
        return new RailwayService(graphFacade);
    }

    @Bean
    @Autowired
    GraphFacade<Station> getGraphFacade(SimpleDirectedWeightedGraph<Station, DefaultWeightedEdge> graph, AlgorithmFactory<Station> algorithmFactory) throws IOException {
        GraphFacade<Station> railwayGraph = new GraphFacade<>(graph, algorithmFactory);
        railwayGraph.addEdge(Station.A, Station.B, 5);
        railwayGraph.addEdge(Station.B, Station.C, 4);
        railwayGraph.addEdge(Station.C, Station.D, 8);
        railwayGraph.addEdge(Station.D, Station.C, 8);
        railwayGraph.addEdge(Station.D, Station.E, 6);
        railwayGraph.addEdge(Station.A, Station.D, 5);
        railwayGraph.addEdge(Station.C, Station.E, 2);
        railwayGraph.addEdge(Station.E, Station.B, 3);
        railwayGraph.addEdge(Station.A, Station.E, 7);
        return railwayGraph;
    }

    @Bean
    SimpleDirectedWeightedGraph<Station, DefaultWeightedEdge> getSimpleDirectedWeightedGraph() {
        return new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
    }

    @Bean
    @Autowired
    AlgorithmFactory<Station> getAlgorithmFactory(SimpleDirectedWeightedGraph<Station, DefaultWeightedEdge> graph) {
        return new AlgorithmFactory<>(graph);
    }
}
