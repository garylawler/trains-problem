import com.component.GraphFacade;
import com.component.NamedVertex;
import com.config.AppConfig;
import com.exception.PathNotFoundException;
import com.service.RailwayService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyClass {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);


        GraphFacade graph = ctx.getBean(GraphFacade.class);
        RailwayService railwayService = ctx.getBean(RailwayService.class);

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

        System.out.println(railwayService.getRouteLength(NamedVertex.A, NamedVertex.B, NamedVertex.C));
        System.out.println(railwayService.getRouteLength(NamedVertex.A, NamedVertex.D));
        System.out.println(railwayService.getRouteLength(NamedVertex.A, NamedVertex.D, NamedVertex.C));
        System.out.println(railwayService.getRouteLength(NamedVertex.A, NamedVertex.E, NamedVertex.B, NamedVertex.C, NamedVertex.D));
        try {
            System.out.println(railwayService.getRouteLength(NamedVertex.A, NamedVertex.E, NamedVertex.D));
        } catch (PathNotFoundException e) {
            System.out.println("NO SUCH ROUTE");
        }





        System.out.println("6-7 -------------------------");

        System.out.println(graph.getPathsWithStops(NamedVertex.C, NamedVertex.C, 3));

        System.out.println(graph.getPathsWithExactNodes(NamedVertex.A, NamedVertex.C, 4));






        System.out.println("8-9 ----------------------");
        System.out.println("8:" + railwayService.getShortestAcyclicPathLength(NamedVertex.A, NamedVertex.C));

        //System.out.println("9:" + railwayService.getShortestLoopLength());
        System.out.println("9:" + railwayService.getCycles());
        System.out.println("9(2):" + railwayService.getCycleIncludingVertex(NamedVertex.B));







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
