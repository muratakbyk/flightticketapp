package flight.flightticketapp.business;

import flight.flightticketapp.business.abstracts.FlightTicketAbstract;
import flight.flightticketapp.core.utilities.Result;
import flight.flightticketapp.entities.*;
import flight.flightticketapp.repo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class FlightTicketImpTest {

    FlightTicket flightTicket;
    Flight flight;
    Flight flight1;
    Route route;
    AirlineCompany airlineCompany;
    Airport airport;
    Airport airport2;
    SellFlightTicket sellFlightTicket;
    @Autowired
    FlightTicketRepository flightTicketRepository;
    @Autowired
    AirportRepository airportRepository;
    @Autowired
    AirlineCompanyRepository airlineCompanyRepository;
    @Autowired
    RouteRepository routeRepository;
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    FlightTicketAbstract flightTicketAbstract;
    @Autowired
    SellFlightTicketRepository sellFlightTicketRepository;
    @BeforeEach
    void setUp() throws ParseException {
        airport = new Airport();
        airport2 = new Airport();
        airport.setName("ISTANBUL AIRPORT");
        airport2.setName("SABIHA GOKCEN");
        airport.setCity("ISTANBUL");
        airport2.setCity("ISTANBUL");
        airport.setCountry("TURKEY");
        airport2.setCountry("TURKEY");
        airportRepository.save(airport);
        airportRepository.save(airport2);

        airlineCompany = new AirlineCompany();
        airlineCompany.setName("THY");
        airlineCompanyRepository.save(airlineCompany);


        route = new Route();
        route.setDepartureAirport(airport);
        route.setArrivalAirport(airport);
        routeRepository.save(route);



        flight = new Flight();
        flight.setRoute(route);
        flight.setFlightAirlineCompany(airlineCompany);
        flight.setFlightSeat(20);
        flight.setFlightPrice(200);
        flight.setDate(new Date());
        flightRepository.save(flight);

        flight1 = new Flight();
        flight1.setRoute(route);
        flight1.setFlightAirlineCompany(airlineCompany);
        flight1.setFlightSeat(20);
        flight1.setFlightPrice(100);
        flight1.setDate(new Date());
        flightRepository.save(flight1);

        sellFlightTicket = new SellFlightTicket();
        sellFlightTicket.setCardNumber("1234567891234567");
        sellFlightTicket.setCardName("CREDIT");
        sellFlightTicket.setCardDay("10");
        sellFlightTicket.setCardMonth("04");
        sellFlightTicket.setCardYear("2024");
        sellFlightTicket.setCardCvv("344");
        sellFlightTicketRepository.save(sellFlightTicket);

        flightTicket = new FlightTicket();
        flightTicket.setFlight(flight1);
        flightTicket.setFlightTicketSell(sellFlightTicket);
        flightTicket.setValid(true);
        flightTicket.setDate(new Date());
        flightTicket.setPrice(100);
        flightTicketRepository.save(flightTicket);
        }

        @Test
        void createFlightTicketTest() {
        assertEquals(flightRepository.getById(flight.getId()).getFlightTickets().size(),0);
        Result result = flightTicketAbstract.createFlightTicket(flight.getId(),sellFlightTicket);
        FlightTicket flightTicket1 = flightRepository.getById(flight.getId()).getFlightTickets().get(0);
        assertNotNull(flightTicket1);
        assertEquals(result.isSuccess(),true);
        assertEquals(flightRepository.getById(flight.getId()).getFlightTickets().size(),1);
        assertEquals(flightTicket1.getFlight().getId(),flight.getId());
        }

        @Test
        void deleteFlightTicketTest(){
        assertNotNull(flightTicket);
        assertEquals(flightTicketRepository.findAll().size(),1);
        Result result = flightTicketAbstract.delete(flightTicket.getId());
        assertEquals(result.isSuccess(),true);
        assertEquals(flightTicketRepository.findAll().size(),0);
        }

        @Test
        void getFlightTicketByIdTest(){
        FlightTicket flightTicket1 = flightTicketAbstract.getById(flightTicket.getId()).getData();
        assertNotNull(flightTicket1);
        assertEquals(flightTicket1.getId(),flightTicket.getId());
        }
}
