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
    String airportName3 = "ATATUK AIRPORT";
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
        Result result = airportAbstract.createAirport(airportName3,"Istanbul","Turkey");
        Airport insertedAirport = airportRepository.findByName(airportName3).get(0);
        assertNotNull(insertedAirport);
        assertEquals(result.isSuccess(), true);
        assertEquals(insertedAirport.getName(),airportName3);

    }

    @Test
    void createAirportNullNameTest(){
        Result result = airportAbstract.createAirport(null,"Istanbul","Turkey");
        assertEquals(result.isSuccess(),false);
    }

    @Test
    void deleteAirportTest(){
       assertNotNull(airport);
       assertNotNull(airportnew);
       assertEquals(airportRepository.findAll().size(),2);
       Result result1 = airportAbstract.delete(airport.getId());
       Result result2 = airportAbstract.delete(airportnew.getId());
       assertEquals(result1.isSuccess(),true);
       assertEquals(result2.isSuccess(),true);
       assertEquals(airportRepository.findAll().size(),0);
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
        assertEquals(airportAbstract.getAirports().getData().size(),2);
        assertNotNull(airportAbstract.getAirports());
    }

    @Test
    void getAirportsByName(){
      Airport airport3 = airportAbstract.getAirportByAirportName(airport.getName()).getData().get(0);
      assertNotNull(airport3);
      assertEquals(airport3.getId(),airport.getId());
      assertEquals(airport3.getName(),airport.getName());
    }
}
