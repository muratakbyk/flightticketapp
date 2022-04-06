package flight.flightticketapp.repo;

import flight.flightticketapp.core.utilities.DataResult;
import flight.flightticketapp.entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport,Integer> {


    List<Airport>findByName(String name);



}
