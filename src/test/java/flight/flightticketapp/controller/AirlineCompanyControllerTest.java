package flight.flightticketapp.controller;

import com.google.gson.Gson;
import flight.flightticketapp.core.utilities.DataResult;
import flight.flightticketapp.core.utilities.Result;
import flight.flightticketapp.entities.AirlineCompany;
import flight.flightticketapp.repo.AirlineCompanyRepository;
import org.aspectj.lang.annotation.Before;
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
public class AirlineCompanyControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    AirlineCompanyRepository airlineCompanyRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    AirlineCompany airlineCompany = new AirlineCompany();
    AirlineCompany airlineCompany2 = new AirlineCompany();

    @BeforeEach
    void setUp(){
        airlineCompany.setName("PEGASUS");
        airlineCompany2.setName("RYANAIR");
        airlineCompanyRepository.save(airlineCompany);
        airlineCompanyRepository.save(airlineCompany2);
    }

    @Test
    public void testCreateAirlineCompanyPost() {
        StringBuilder testUrl = new StringBuilder();
        testUrl.append("http://localhost:");
        testUrl.append(port);
        testUrl.append("/airlinecompany");
        testUrl.append("/airlineCreate");
        testUrl.append("?name={name}");
        ResponseEntity<Result> responseEntity = restTemplate.postForEntity(testUrl.toString(),
                null,
                Result.class,
                "THY");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }


    @Test
    public void testFindAirlineCompanyByName(){
        StringBuilder testUrl = new StringBuilder();
        testUrl.append("http://localhost:");
        testUrl.append(port);
        testUrl.append("/airlinecompany");
        testUrl.append("/airlineGetByName");
        testUrl.append("?name={name}");
        ResponseEntity<AirlineCompany> responseEntity = restTemplate.getForEntity(testUrl.toString(),
                AirlineCompany.class,
                airlineCompany.getName());
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }
    @Test
    public void testAirlineGetById(){
        StringBuilder testUrl = new StringBuilder();
        testUrl.append("http://localhost:");
        testUrl.append(port);
        testUrl.append("/airlinecompany");
        testUrl.append("/airlineGetById");
        testUrl.append("?id={id}");
        ResponseEntity<AirlineCompany> responseEntity = restTemplate.getForEntity(testUrl.toString(),
                AirlineCompany.class,
                airlineCompany.getId());
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }
    @Test
    public void testGetAllAirlineCompanies(){
        StringBuilder testUrl = new StringBuilder();
        testUrl.append("http://localhost:");
        testUrl.append(port);
        testUrl.append("/airlinecompany");
        testUrl.append("/allAirlineCompanies");
        ResponseEntity<AirlineCompany> responseEntity = restTemplate.getForEntity(testUrl.toString(),
                AirlineCompany.class);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteAirlineCompany() {
        StringBuilder testUrl = new StringBuilder();
        testUrl.append("http://localhost:");
        testUrl.append(port);
        testUrl.append("/airlinecompany");
        testUrl.append("/airlineDelete");
        testUrl.append("?id={id}");
        ResponseEntity<Result> responseEntity = restTemplate.postForEntity(testUrl.toString(),
                null,
                Result.class,
                airlineCompany.getId());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
