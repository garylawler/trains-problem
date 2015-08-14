import com.config.AppConfig;
import com.exception.PathNotFoundException;
import com.model.Station;
import com.service.RailwayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TrainsProblem {

    static final Logger console = LoggerFactory.getLogger(TrainsProblem.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        RailwayService railwayService = ctx.getBean(RailwayService.class);

        console.warn("Questions 1-5 ----------------------\n");
        console.info("1: " + railwayService.getRouteLength(Station.A, Station.B, Station.C));
        console.info("2: " + railwayService.getRouteLength(Station.A, Station.D));
        console.info("3: " + railwayService.getRouteLength(Station.A, Station.D, Station.C));
        console.info("4: " + railwayService.getRouteLength(Station.A, Station.E, Station.B, Station.C, Station.D));
        try {
            console.info("5: " + railwayService.getRouteLength(Station.A, Station.E, Station.D));
        } catch (PathNotFoundException e) {
            console.info("NO SUCH ROUTE");
        }

        console.info("\nQuestions 6-7 -------------------------\n");
        console.info("6: " + railwayService.getNumberOfRoutesWithStops(Station.C, Station.C, 3));
        console.info("7: " + railwayService.getNumberOfRoutesWithExactNumberOfStops(Station.A, Station.C, 4));

        console.info("\nQuestions 8-9 ----------------------\n");
        console.info("8: " + railwayService.getShortestNonLoopingRouteLength(Station.A, Station.C));
        console.info("9: " + railwayService.getShortestLoopLengthIncludingGivenStation(Station.B));

        console.info("\nQuestion 10 ------------------------\n");
        console.info("10: " + railwayService.getNumberOfRoutesIncludingStationWithinRadius(Station.C, 30));

        console.info("-------------------------");
    }
}
