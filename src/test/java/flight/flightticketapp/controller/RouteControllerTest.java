package flight.flightticketapp.controller;

import flight.flightticketapp.core.utilities.Result;
import flight.flightticketapp.entities.Airport;
import flight.flightticketapp.entities.Flight;
import flight.flightticketapp.entities.Route;
import flight.flightticketapp.repo.AirportRepository;
import flight.flightticketapp.repo.RouteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RouteControllerTest {
    @LocalServerPort
    int port;
    @Autowired
    AirportRepository airportRepository;

    @Autowired
    RouteRepository routeRepository;
    @Autowired
    private TestRestTemplate restTemplate;

    Airport airport;
    Airport airport1;
    Route route;
    String airportName = "Istanbul Airport";
    String airportName1 = "Sabiha Gokcen Airport";

    @BeforeEach
    void setUp(){
        airport = new Airport();
        airport.setName(airportName);
        airport1 = new Airport();
        airport1.setName(airportName1);
        airportRepository.save(airport);
        airportRepository.save(airport1);

        route = new Route();
        route.setDepartureAirport(airport);
        route.setArrivalAirport(airport1);
        routeRepository.save(route);

    }

    @Test
    public void testCreateRoute() {
        StringBuilder testUrl = new StringBuilder();
        testUrl.append("http://localhost:");
        testUrl.append(port);
        testUrl.append("/route");
        testUrl.append("/createRoute");
        testUrl.append("?departureId={departureId}&");
        testUrl.append("arrivalId={arrivalId}");

        ResponseEntity<Result> responseEntity = restTemplate.postForEntity(testUrl.toString(),
                null,
                Result.class,
                airport.getId(),
                airport1.getId()
                );
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteRoute() {
        StringBuilder testUrl = new StringBuilder();
        testUrl.append("http://localhost:");
        testUrl.append(port);
        testUrl.append("/route");
        testUrl.append("/deleteRoute");
        testUrl.append("?id={id}");
        ResponseEntity<Result> responseEntity = restTemplate.postForEntity(testUrl.toString(),
                null,
                Result.class,
                route.getId());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testRouteGetById(){
        StringBuilder testUrl = new StringBuilder();
        testUrl.append("http://localhost:");
        testUrl.append(port);
        testUrl.append("/route");
        testUrl.append("/routeGetById");
        testUrl.append("?id={id}");
        ResponseEntity<Route> responseEntity = restTemplate.getForEntity(testUrl.toString(),
                Route.class,
                route.getId());
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }
    @Test
    public void testRouteGetByDepartureAndArrivalId(){
        StringBuilder testUrl = new StringBuilder();
        testUrl.append("http://localhost:");
        testUrl.append(port);
        testUrl.append("/route");
        testUrl.append("/getRouteByDepartureAndArrivalAirportId");
        testUrl.append("?departureAirportId={departureAirportId}&");
        testUrl.append("arrivalAirportId={arrivalAirportId}");

        ResponseEntity<Route> responseEntity = restTemplate.getForEntity(testUrl.toString(),
                Route.class,
                airport.getId(),airport1.getId());
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }



}
