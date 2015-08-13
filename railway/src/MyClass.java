import com.model.Station;
import com.config.AppConfig;
import com.exception.PathNotFoundException;
import com.service.RailwayService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyClass {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        RailwayService railwayService = ctx.getBean(RailwayService.class);



        System.out.println("1-5 ----------------------");
        System.out.println("1:" + railwayService.getRouteLength(Station.A, Station.B, Station.C));
        System.out.println("2:" + railwayService.getRouteLength(Station.A, Station.D));
        System.out.println("3:" + railwayService.getRouteLength(Station.A, Station.D, Station.C));
        System.out.println("4:" + railwayService.getRouteLength(Station.A, Station.E, Station.B, Station.C, Station.D));
        try {
            System.out.println("5:" + railwayService.getRouteLength(Station.A, Station.E, Station.D));
        } catch (PathNotFoundException e) {
            System.out.println("NO SUCH ROUTE");
        }

        System.out.println("6-7 -------------------------");
        System.out.println("6:" + railwayService.getPathsWithStops(Station.C, Station.C, 3));
        System.out.println("7:" + railwayService.getNumberOfRoutesWithExactNumberOfStops(Station.A, Station.C, 4));

        System.out.println("8-9 ----------------------");
        System.out.println("8:" + railwayService.getShortestNonLoopingRouteLength(Station.A, Station.C));
        System.out.println("9:" + railwayService.getShortestLoopLengthIncludingGivenStation(Station.B));

//
//        System.out.println("10 ------------------------");
//
//        for(List<com.model.Station> cycle : graph.getCycles()) {
//            graph.getCycleLength(cycle);
//        }
//
//        System.out.println("-------------------------");
//        System.out.print("");
    }

}
