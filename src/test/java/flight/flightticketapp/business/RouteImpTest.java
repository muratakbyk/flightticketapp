package flight.flightticketapp.business;

import flight.flightticketapp.business.abstracts.RouteAbstract;
import flight.flightticketapp.core.utilities.Result;
import flight.flightticketapp.entities.Airport;
import flight.flightticketapp.entities.Route;
import flight.flightticketapp.repo.AirportRepository;
import flight.flightticketapp.repo.RouteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RouteImpTest {
    Airport airport;
    Airport airport2;
    Route route;
    Route route1;
    @Autowired
    RouteAbstract routeAbstract;
    @Autowired
    RouteRepository routeRepository;
    @Autowired
    AirportRepository airportRepository;

    @BeforeEach
    void setUp(){
        airport = new Airport();
        airport2 = new Airport();

        airport.setName("ISTANBUL AIRPORT");
        airport.setCity("ISTANBUL");
        airport.setCountry("TURKEY");

        airport2.setName("SABIHA GOKCEN");
        airport2.setCity("ISTANBUL");
        airport2.setCountry("TURKEY");

        airportRepository.save(airport);
        airportRepository.save(airport2);


        route = new Route();
        route.setDepartureAirport(airport);
        route.setArrivalAirport(airport2);
        routeRepository.save(route);
    }

    @Test
    void createRouteTest(){
        Result result = routeAbstract.createRoute(airport2.getId(),airport.getId());
        Route insertedRoute = routeRepository.findByDepartureAirportId(airport2.getId()).get(0);
        assertNotNull(insertedRoute);
        assertEquals(result.isSuccess(),true);
        assertEquals(insertedRoute.getDepartureAirport().getId(),airport2.getId());
    }

    @Test
    void deleteRouteTest(){
     assertNotNull(route);
     assertEquals(routeRepository.findAll().size(),1);
     Result result = routeAbstract.delete(route.getId());
     assertEquals(result.isSuccess(),true);
     assertEquals(routeRepository.findAll().size(),0);
    }

    @Test
    void findRouteByDepartureAirportTest(){
        Route route2 = routeAbstract.findByDepartureAirportId(airport.getId()).getData().get(0);
        assertNotNull(route2);
        assertEquals(route.getId(),route2.getId());
        assertEquals(route.getDepartureAirport().getId(),route2.getDepartureAirport().getId());
        assertEquals(route.getArrivalAirport().getId(),route2.getArrivalAirport().getId());
    }

    @Test
    void createRouteSameDepartureAndArrivalAirportTest(){
        assertEquals(routeRepository.findByArrivalAirportId(airport.getId()).size(),0);
        Result result = routeAbstract.createRoute(airport.getId(),airport.getId());
        assertEquals(result.isSuccess(),false);
        assertEquals(routeRepository.findByArrivalAirportId(airport.getId()).size(),0);
    }
    @Test
    void findByDepartureAndArrivalAirportIdTest(){
        assertEquals(routeAbstract.findByDepartureAndArrivalAirportId(airport.getId(),airport2.getId()).getData().size(),1);
        Route route2 = routeAbstract.findByDepartureAndArrivalAirportId(airport.getId(),airport2.getId()).getData().get(0);
        assertEquals(route2.getId(),route.getId());
        assertEquals(route2.getDepartureAirport().getId(),airport.getId());
        assertEquals(route2.getArrivalAirport().getId(),airport2.getId());
    }

}
