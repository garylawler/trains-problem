package com.config;

import com.algorithm.impl.AlgorithmFactory;
import com.facade.GraphFacade;
import com.model.Station;
import com.service.RailwayService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Configuration
@PropertySource("application.properties")
public class AppConfig {

    @Autowired
    Environment env;

    @Bean
    @Autowired
    RailwayService getRailwayService(GraphFacade graphFacade) {
        return new RailwayService(graphFacade);
    }

    @Bean
    @Autowired
    GraphFacade<Station> getGraphFacade(SimpleDirectedWeightedGraph<Station, DefaultWeightedEdge> graph, AlgorithmFactory<Station> algorithmFactory) throws IOException {
        GraphFacade<Station> railwayGraph = new GraphFacade<>(graph, algorithmFactory);

        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(env.getProperty("railway.map.file"));
        CSVParser parser = new CSVParser(new InputStreamReader(stream), CSVFormat.DEFAULT);
        for(CSVRecord csvRecord : parser.getRecords()) {
            railwayGraph.addEdge(Station.valueOf(csvRecord.get(0)),
                    Station.valueOf(csvRecord.get(1)),
                    Integer.parseInt(csvRecord.get(2)));
        }
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
