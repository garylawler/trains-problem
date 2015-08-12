import com.component.GraphFacade;
import com.component.NamedVertex;
import com.config.AppConfig;
import com.exception.PathNotFoundException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MyClass {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);


        GraphFacade graph = ctx.getBean(GraphFacade.class);

        graph.addEdge(NamedVertex.A, NamedVertex.B, 5);
        graph.addEdge(NamedVertex.B, NamedVertex.C, 4);
        graph.addEdge(NamedVertex.C, NamedVertex.D, 8);
        graph.addEdge(NamedVertex.D, NamedVertex.C, 8);
        graph.addEdge(NamedVertex.D, NamedVertex.E, 6);
        graph.addEdge(NamedVertex.A, NamedVertex.D, 5);
        graph.addEdge(NamedVertex.C, NamedVertex.E, 2);
        graph.addEdge(NamedVertex.E, NamedVertex.B, 3);
        graph.addEdge(NamedVertex.A, NamedVertex.E, 7);




        System.out.println("1-5 ----------------------");

        System.out.println(graph.getEdgeWeight(NamedVertex.A, NamedVertex.B) + graph.getEdgeWeight(NamedVertex.B, NamedVertex.C));
        System.out.println(graph.getEdgeWeight(NamedVertex.A, NamedVertex.D));
        System.out.println(graph.getEdgeWeight(NamedVertex.A, NamedVertex.D) + graph.getEdgeWeight(NamedVertex.D, NamedVertex.C));
        System.out.println(graph.getEdgeWeight(NamedVertex.A, NamedVertex.E) + graph.getEdgeWeight(NamedVertex.E, NamedVertex.B) + graph.getEdgeWeight(NamedVertex.B, NamedVertex.C) + graph.getEdgeWeight(NamedVertex.C, NamedVertex.D));
        try {
            System.out.println(graph.getEdgeWeight(NamedVertex.A, NamedVertex.E) + graph.getEdgeWeight(NamedVertex.E, NamedVertex.D));
        } catch (PathNotFoundException e) {
            System.out.println("NO SUCH ROUTE");
        }





        System.out.println("6-7 -------------------------");

        System.out.println(graph.getPathsWithStops(NamedVertex.C, NamedVertex.C, 3));

        System.out.println(graph.getPathsWithExactNodes(NamedVertex.A, NamedVertex.C, 4));






        System.out.println("8-9 ----------------------");
        System.out.println("8:" + graph.getShortestAcyclicPathLength(NamedVertex.A, NamedVertex.C));

        double shortestLength = Integer.MAX_VALUE;
        for (List<NamedVertex> cycle : graph.getCycles()) {
            double cycleLength = graph.getCycleLength(cycle);
            shortestLength = (cycleLength < shortestLength) ? cycleLength : shortestLength;
        }
        System.out.println("9:" + shortestLength);







//
//        System.out.println("10 ------------------------");
//
//        for(List<com.component.NamedVertex> cycle : graph.getCycles()) {
//            graph.getCycleLength(cycle);
//        }
//
//        System.out.println("-------------------------");
//        System.out.print("");
    }

}
