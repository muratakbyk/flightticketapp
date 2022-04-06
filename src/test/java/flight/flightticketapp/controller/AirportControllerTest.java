package flight.flightticketapp.controller;

import flight.flightticketapp.core.utilities.Result;
import flight.flightticketapp.entities.Airport;
import flight.flightticketapp.repo.AirportRepository;
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
public class AirportControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    AirportRepository airportRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    Airport airport = new Airport();

    @BeforeEach
    void setUp(){
        airport.setName("ISTANBUL AIRPORT");
        airport.setCountry("TURKEY");
        airport.setCity("ISTANBUL");
        airportRepository.save(airport);
    }

    @Test
    public void testCreateAirport() {
        StringBuilder testUrl = new StringBuilder();
        testUrl.append("http://localhost:");
        testUrl.append(port);
        testUrl.append("/airport");
        testUrl.append("/createAirport");
        testUrl.append("?name={name}&");
        testUrl.append("city={city}&");
        testUrl.append("country={country}");

        ResponseEntity<Result> responseEntity = restTemplate.postForEntity(testUrl.toString(),
                null,
                Result.class,
                "SABIHA GOKCEN","ISTANBUL","TURKEY");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }


    @Test
    public void testFindAirportByName(){
        StringBuilder testUrl = new StringBuilder();
        testUrl.append("http://localhost:");
        testUrl.append(port);
        testUrl.append("/airport");
        testUrl.append("/airportGetByName");
        testUrl.append("?name={name}");
        ResponseEntity<Airport> responseEntity = restTemplate.getForEntity(testUrl.toString(),
                Airport.class,
                airport.getName());
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }
    @Test
    public void testAirportGetById(){
        StringBuilder testUrl = new StringBuilder();
        testUrl.append("http://localhost:");
        testUrl.append(port);
        testUrl.append("/airport");
        testUrl.append("/airportGetById");
        testUrl.append("?id={id}");
        ResponseEntity<Airport> responseEntity = restTemplate.getForEntity(testUrl.toString(),
                Airport.class,
                airport.getId());
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }


    @Test
    public void testDeleteAirport() {
        StringBuilder testUrl = new StringBuilder();
        testUrl.append("http://localhost:");
        testUrl.append(port);
        testUrl.append("/airport");
        testUrl.append("/deleteAirport");
        testUrl.append("?id={id}");
        ResponseEntity<Result> responseEntity = restTemplate.postForEntity(testUrl.toString(),
                null,
                Result.class,
                airport.getId());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
