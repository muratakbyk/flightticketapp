package flight.flightticketapp.business;

import flight.flightticketapp.business.abstracts.FlightAbstract;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FlightImpTest {

    Flight flight;
    AirlineCompany airlineCompany;
    Airport airport;
    Airport airport2;
    Route route;
    Route route2;
    int flightSeatCount = 30;
    double flightPrice = 300;

    String departureDate = "2022-09-11 12:10:40";
    String departureDate2 = "2022-10-05 12:10:40";

    @Autowired
    FlightAbstract flightAbstract;
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    RouteRepository routeRepository;
    @Autowired
    AirportRepository airportRepository;
    @Autowired
    AirlineCompanyRepository airlineCompanyRepository;
    @BeforeEach
    void setUp() throws ParseException {
        airport = new Airport();
        airport2 = new Airport();

        airport.setName("SABIHA GOKCEN");
        airport.setCity("ISTANBUL");
        airport.setCountry("TURKEY");
        airport2.setName("ISTANBUL AIRPORT");
        airport2.setCity("ISTANBUL");
        airport2.setCountry("TURKEY");
        airportRepository.save(airport);
        airportRepository.save(airport2);

        route= new Route();
        route2 = new Route();
        route.setDepartureAirport(airport);
        route.setArrivalAirport(airport2);
        route2.setDepartureAirport(airport2);
        route2.setArrivalAirport(airport2);
        routeRepository.save(route);
        routeRepository.save(route2);

        airlineCompany = new AirlineCompany();
        airlineCompany.setName("THY");
        airlineCompanyRepository.save(airlineCompany);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        formatter.setTimeZone(TimeZone.getTimeZone("Turkey"));
        Date departure = formatter.parse(departureDate);

        flight = new Flight();
        flight.setFlightAirlineCompany(airlineCompany);
        flight.setRoute(route);
        flight.setFlightSeat(flightSeatCount);
        flight.setFlightPrice(flightPrice);
        flight.setDate(departure);
        flightRepository.save(flight);
    }

    @Test
    void createFlightTest(){

        Result result = flightAbstract.createFlight(route.getId(),airlineCompany.getId(),20,200,new Date());
        Flight insertedFlight = flightRepository.findAll().get(1);
        assertEquals(result.isSuccess(),true);
        assertEquals(insertedFlight.getFlightPrice(),200);
        assertEquals(insertedFlight.getFlightSeat(),20);
    }

    @Test
    void deleteFlightTest(){
        assertNotNull(flight);
        assertEquals(flightRepository.findAll().size(),1);
        Result result = flightAbstract.delete(flight.getId());
        assertEquals(result.isSuccess(),true);
        assertEquals(flightRepository.findAll().size(),0);
    }

    @Test
    void getByFlightIdTest(){
        Flight flight1 = flightAbstract.getById(flight.getId()).getData();
        assertNotNull(flight1);
        assertEquals(flight1.getId(),flight.getId());
    }

    @Test
    void getAllFlightsTest(){
        Result result = flightAbstract.getAllFlights();
        assertNotNull(result);
        assertEquals(result.isSuccess(),true);
        assertEquals(flightAbstract.getAllFlights().getData().size(),1);
    }
}
