package flight.flightticketapp.business;

import flight.flightticketapp.business.abstracts.AirlineCompanyAbstract;
import flight.flightticketapp.core.messages.Messages;
import flight.flightticketapp.core.utilities.Result;
import flight.flightticketapp.core.utilities.SuccessDataResult;
import flight.flightticketapp.entities.AirlineCompany;
import flight.flightticketapp.repo.AirlineCompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AirlineCompanyImpTest {

    @Autowired
    AirlineCompanyAbstract airlineCompanyAbstract;
    @Autowired
    AirlineCompanyRepository airlineCompanyRepository;

    AirlineCompany airlineCompany;
    String airlineCompanyName = "RYANAIR";
    String airlineCompanyName2 = "THY";
    @BeforeEach
    void setUp(){
    airlineCompany = new AirlineCompany();
    airlineCompany.setName(airlineCompanyName2);
    airlineCompanyRepository.save(airlineCompany);

    }

    @Test
    void createAirlineCompanyTest(){
        Result result = airlineCompanyAbstract.createAirlineCompany(airlineCompanyName);
        AirlineCompany insertedAirline = airlineCompanyRepository.findByName(airlineCompanyName).get(0);
        assertNotNull(insertedAirline);
        assertEquals(result.isSuccess(), true);
        assertEquals(insertedAirline.getName(),airlineCompanyName);
    }

    @Test
    void createAirlineCompanyNullNameTest(){
        Result result = airlineCompanyAbstract.createAirlineCompany(null);
        assertEquals(result.isSuccess(),false);
    }

    @Test
    void deleteAirlineCompanyTest(){
        assertNotNull(airlineCompany);
        assertEquals(airlineCompanyRepository.findAll().size(),1);
        Result result = airlineCompanyAbstract.delete(airlineCompany.getId());
        assertEquals(result.isSuccess(),true);
        assertEquals(airlineCompanyRepository.findAll().size(),0);
    }

    @Test
    void getAirlineCompanyByIdTest(){
        AirlineCompany airlineCompany1 = airlineCompanyAbstract.getById(airlineCompany.getId()).getData();
        assertNotNull(airlineCompany1);
        assertEquals(airlineCompany1.getId(),airlineCompany.getId());
        assertEquals(airlineCompany1.getName(),airlineCompany.getName());

    }

    @Test
    void getAllAirlineCompaniesTest(){
        assertEquals(airlineCompanyAbstract.getAllAirlineCompanies().getData().size(),1);
        assertNotNull(airlineCompanyAbstract.getAllAirlineCompanies());
    }

    @Test
    void getAirlineCompanyByName(){
        AirlineCompany airlineCompany1 = airlineCompanyAbstract.
                getAirlineCompaniesByName(airlineCompany.getName()).getData().get(0);
        assertNotNull(airlineCompany1);
        assertEquals(airlineCompany1.getId(),airlineCompany.getId());
        assertEquals(airlineCompany1.getName(),airlineCompany.getName());

    }


}
