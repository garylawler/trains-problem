import org.jgrapht.WeightedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.junit.Test;

public class EasyWinTest {

    private WeightedGraph<String, DefaultWeightedEdge> directedGraph;

    @Test
    public void justUseLibrary() {
        directedGraph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);

        directedGraph.addVertex("a");
        directedGraph.addVertex("b");
        directedGraph.addVertex("c");
        directedGraph.addVertex("d");
        directedGraph.addVertex("e");
        directedGraph.setEdgeWeight(directedGraph.addEdge("a", "b"), 5);
        directedGraph.setEdgeWeight(directedGraph.addEdge("b", "c"), 4);
        directedGraph.setEdgeWeight(directedGraph.addEdge("c", "d"), 8);
        directedGraph.setEdgeWeight(directedGraph.addEdge("d", "c"), 8);
        directedGraph.setEdgeWeight(directedGraph.addEdge("d", "e"), 8);
        directedGraph.setEdgeWeight(directedGraph.addEdge("a", "d"), 5);
        directedGraph.setEdgeWeight(directedGraph.addEdge("c", "e"), 2);
        directedGraph.setEdgeWeight(directedGraph.addEdge("e", "b"), 3);
        directedGraph.setEdgeWeight(directedGraph.addEdge("a", "e"), 7);

        System.out.println(directedGraph.getEdgeWeight(directedGraph.getEdge("a", "b")) + directedGraph.getEdgeWeight(directedGraph.getEdge("b", "c")));
        System.out.println(directedGraph.getEdgeWeight(directedGraph.getEdge("a", "d")));
        System.out.println(directedGraph.getEdgeWeight(directedGraph.getEdge("a", "d")) + directedGraph.getEdgeWeight(directedGraph.getEdge("d", "c")));
        System.out.println(directedGraph.getEdgeWeight(directedGraph.getEdge("a", "e")) + directedGraph.getEdgeWeight(directedGraph.getEdge("e", "d")));

        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstraShortestPath = new DijkstraShortestPath<>(directedGraph, "a", "d");
        dijkstraShortestPath.getPathLength();


        System.out.print("");

    }

}
