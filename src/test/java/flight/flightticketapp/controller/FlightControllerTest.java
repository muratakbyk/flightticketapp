package flight.flightticketapp.controller;

import flight.flightticketapp.core.utilities.Result;
import flight.flightticketapp.entities.AirlineCompany;
import flight.flightticketapp.entities.Airport;
import flight.flightticketapp.entities.Flight;
import flight.flightticketapp.entities.Route;
import flight.flightticketapp.repo.AirlineCompanyRepository;
import flight.flightticketapp.repo.AirportRepository;
import flight.flightticketapp.repo.FlightRepository;
import flight.flightticketapp.repo.RouteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FlightControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    FlightRepository flightRepository;
    @Autowired
    RouteRepository routeRepository;
    @Autowired
    AirlineCompanyRepository airlineCompanyRepository;

    @Autowired
    AirportRepository airportRepository;

    @Autowired
    private TestRestTemplate restTemplate;
    Flight flight;
    Airport airport;
    Airport airport1;
    Route route;
    AirlineCompany airlineCompany;
    int flightSeatCount = 50;
    double flightPrice = 200;
    String date = "2022-09-22 10:15:55";
    @BeforeEach
    void setUp(){

        airport = new Airport();
        airport1 = new Airport();
        airport.setName("SABIHA GOKCEN");
        airport1.setName("ISTANBUL");
        airportRepository.save(airport);
        airportRepository.save(airport1);

        route = new Route();
        route.setArrivalAirport(airport);
        route.setDepartureAirport(airport1);
        routeRepository.save(route);

        airlineCompany = new AirlineCompany();
        airlineCompany.setName("THY");
        airlineCompanyRepository.save(airlineCompany);

        flight = new Flight();
        flight.setFlightPrice(flightPrice);
        flight.setFlightSeat(flightSeatCount);
        flight.setRoute(route);
        flight.setFlightAirlineCompany(airlineCompany);
        flight.setDate(new Date());
        flightRepository.save(flight);
    }
    @Test
    public void testCreateFlight() {
        StringBuilder testUrl = new StringBuilder();
        testUrl.append("http://localhost:");
        testUrl.append(port);
        testUrl.append("/flight");
        testUrl.append("/createFlight");
        testUrl.append("?routeId={routeId}&");
        testUrl.append("airlinesCompanyId={airlinesCompanyId}&");
        testUrl.append("flightSeatCount={flightSeatCount}&");
        testUrl.append("flightPrice={flightPrice}&");
        testUrl.append("date={date}");

        ResponseEntity<Result> responseEntity = restTemplate.postForEntity(testUrl.toString(),
                null,
                Result.class,
                route.getId(),
                airlineCompany.getId(),
                flightSeatCount,
                flightPrice,
                date);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteFlight() {
        StringBuilder testUrl = new StringBuilder();
        testUrl.append("http://localhost:");
        testUrl.append(port);
        testUrl.append("/flight");
        testUrl.append("/flightDelete");
        testUrl.append("?id={id}");
        ResponseEntity<Result> responseEntity = restTemplate.postForEntity(testUrl.toString(),
                null,
                Result.class,
                flight.getId());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testFlightGetById(){
        StringBuilder testUrl = new StringBuilder();
        testUrl.append("http://localhost:");
        testUrl.append(port);
        testUrl.append("/flight");
        testUrl.append("/flightGetById");
        testUrl.append("?id={id}");
        ResponseEntity<Flight> responseEntity = restTemplate.getForEntity(testUrl.toString(),
                Flight.class,
                flight.getId());
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    @Test
    public void testGetAllFlights(){
        StringBuilder testUrl = new StringBuilder();
        testUrl.append("http://localhost:");
        testUrl.append(port);
        testUrl.append("/flight");
        testUrl.append("/flightGetAll");
        ResponseEntity<Flight> responseEntity = restTemplate.getForEntity(testUrl.toString(),
                Flight.class);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

}
