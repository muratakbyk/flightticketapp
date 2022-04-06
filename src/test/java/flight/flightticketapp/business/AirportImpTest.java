package flight.flightticketapp.business;

import flight.flightticketapp.business.abstracts.AirlineCompanyAbstract;
import flight.flightticketapp.business.abstracts.AirportAbstract;

import flight.flightticketapp.core.messages.Messages;
import flight.flightticketapp.core.utilities.Result;
import flight.flightticketapp.entities.Airport;
import flight.flightticketapp.repo.AirportRepository;
import org.aspectj.bridge.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class AirportImpTest {

    @Autowired
    AirportAbstract airportAbstract;
    @Autowired
    AirportRepository airportRepository;
    Airport airport;
    Airport airportnew;
    String airportName1="ISTANBUL AIRPORT";
    String airportName2 = "SABIHA GOKCEN AIRPORT";
    @BeforeEach
    void setUp(){
        airport = new Airport();
        airport.setName(airportName1);
        airport.setCity("Istanbul");
        airport.setCountry("Turkey");
        airportRepository.save(airport);

        airportnew = new Airport();
        airportnew.setName(airportName2);
        airportnew.setCity("Istanbul");
        airportnew.setCountry("Turkey");
        airportRepository.save(airportnew);
    }

    @Test
    void createAirportTest(){
        Result result = airportAbstract.createAirport(airportName1,"Istanbul","Turkey");
        Airport insertedAirport = airportRepository.findByName(airportName1).get(0);
        assertNotNull(insertedAirport);
        assertEquals(result.getMessage(), Messages.airportCreated);
        assertEquals(insertedAirport.getName(),airportName1);

    }

    @Test
    void createAirportNullNameTest(){
        Result result = airportAbstract.createAirport(null,"Istanbul","Turkey");
        assertEquals(result.isSuccess(),false);
    }

    @Test
    void deleteAirportTest(){
        Airport airport1 = airportRepository.findByName(airportName2).get(0);
        assertNotNull(airport1);
        assertEquals(airport1.getName(),airportName2);
        assertEquals(airportRepository.findById(airport1.getId()).get().getId(),airport1.getId());
        assertEquals(airportRepository.findByName(airportName2).size(),1);
        Result result = airportAbstract.delete(airport1.getId());
        assertEquals(result.isSuccess(),true);
        assertEquals(airportRepository.findByName(airportName2).size(),0);
    }

    @Test
    void getAirportByIdTest(){

        Airport airport2 = airportAbstract.getById(airport.getId()).getData();
        assertNotNull(airport2);
        assertEquals(airport2.getId(),airport.getId());
        assertEquals(airport2.getName(),airportName1);
    }

    @Test
    void getAllAirportsTest(){
        assertEquals(airportAbstract.getAirports().getData().size(),1);
        assertNotNull(airportAbstract.getAirports());
    }

    @Test
    void getAirportsByName(){
        Airport airport4 = airportAbstract.getAirportByAirportName(airportName2).getData().get(0);
        assertNotNull(airport4);
        assertEquals(airport4.getName(),airportnew.getName());
        assertEquals(airport4.getId(),airportnew.getId());

    }
}
