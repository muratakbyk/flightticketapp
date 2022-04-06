package flight.flightticketapp.repo;

import flight.flightticketapp.core.utilities.DataResult;
import flight.flightticketapp.core.utilities.SuccessDataResult;
import flight.flightticketapp.entities.AirlineCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirlineCompanyRepository extends JpaRepository<AirlineCompany,Integer> {

    List<AirlineCompany> findByName(String airlinesCompanyName);



}
