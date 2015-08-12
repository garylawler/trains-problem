package com.config;

import com.factory.AlgorithmFactory;
import com.component.GraphFacade;
import com.component.NamedVertex;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    SimpleDirectedWeightedGraph<NamedVertex, DefaultWeightedEdge> getSimpleDirectedWeightedGraph() {
        SimpleDirectedWeightedGraph<NamedVertex, DefaultWeightedEdge> directedGraph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        return directedGraph;
    }

    @Bean
    @Autowired
    AlgorithmFactory getDijkstraFactory(SimpleDirectedWeightedGraph<NamedVertex, DefaultWeightedEdge> graph) {
        return new AlgorithmFactory(graph);
    }

    @Bean
    @Autowired
    GraphFacade getGraphFacade(SimpleDirectedWeightedGraph<NamedVertex, DefaultWeightedEdge> graph, AlgorithmFactory algorithmFactory) {
        return new GraphFacade(graph, algorithmFactory);
    }
}
