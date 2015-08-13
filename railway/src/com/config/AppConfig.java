package com.config;

import com.Station;
import com.component.GraphFacade;
import com.factory.AlgorithmFactory;
import com.service.RailwayService;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    @Autowired
    RailwayService getRailwayService(GraphFacade graphFacade, AlgorithmFactory algorithmFactory) {
        return new RailwayService(graphFacade);
    }

    @Bean
    @Autowired
    GraphFacade<Station> getGraphFacade(SimpleDirectedWeightedGraph<Station, DefaultWeightedEdge> graph, AlgorithmFactory algorithmFactory) {
        return new GraphFacade<>(graph, algorithmFactory);
    }

    @Bean
    SimpleDirectedWeightedGraph<Station, DefaultWeightedEdge> getSimpleDirectedWeightedGraph() {
        SimpleDirectedWeightedGraph<Station, DefaultWeightedEdge> directedGraph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        return directedGraph;
    }

    @Bean
    @Autowired
    AlgorithmFactory getAlgorithmFactory(SimpleDirectedWeightedGraph<Station, DefaultWeightedEdge> graph) {
        return new AlgorithmFactory<>(graph);
    }
}
