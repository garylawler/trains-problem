import com.config.AppConfig;
import com.exception.PathNotFoundException;
import com.model.Station;
import com.service.RailwayService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TrainsProblem {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        RailwayService railwayService = ctx.getBean(RailwayService.class);

        System.out.println("Questions 1-5 ----------------------\n");
        System.out.println("1: " + railwayService.getRouteLength(Station.A, Station.B, Station.C));
        System.out.println("2: " + railwayService.getRouteLength(Station.A, Station.D));
        System.out.println("3: " + railwayService.getRouteLength(Station.A, Station.D, Station.C));
        System.out.println("4: " + railwayService.getRouteLength(Station.A, Station.E, Station.B, Station.C, Station.D));
        try {
            System.out.println("5: " + railwayService.getRouteLength(Station.A, Station.E, Station.D));
        } catch (PathNotFoundException e) {
            System.out.println("NO SUCH ROUTE");
        }

        System.out.println("\nQuestions 6-7 -------------------------\n");
        System.out.println("6: " + railwayService.getNumberOfRoutesWithStops(Station.C, Station.C, 3));
        System.out.println("7: " + railwayService.getNumberOfRoutesWithExactNumberOfStops(Station.A, Station.C, 4));

        System.out.println("\nQuestions 8-9 ----------------------\n");
        System.out.println("8: " + railwayService.getShortestNonLoopingRouteLength(Station.A, Station.C));
        System.out.println("9: " + railwayService.getShortestLoopLengthIncludingGivenStation(Station.B));

        System.out.println("\nQuestion 10 ------------------------\n");
        System.out.println("10: " + railwayService.getNumberOfRoutesIncludingStationWithinRadius(Station.C, 30));

        System.out.println("-------------------------");
    }
}
