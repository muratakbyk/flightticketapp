package flight.flightticketapp.repo;


import flight.flightticketapp.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {


    List<Route> findByDepartureAirportId(int id);
    List<Route> findByArrivalAirportId(int id);
    List<Route>findByDepartureAirportIdAndAndArrivalAirportId(int departureAirportId,int arrivalAirportId);


    // @Query(value="SELECT a FROM Route a WHERE  a.arrivalAirport.id = ?1")
    //List<Route> findByArrivalAirportId(int arrivalAirportId);

    //  @Query(value="SELECT a FROM Route a WHERE  a.departureAirport.id = ?1")
    // List<Route> findByDepartureAirportId(int departureAirportId);

    //  @Query(value="SELECT a FROM Route a WHERE  a.departureAirport.id = ?1 AND a.arrivalAirport.id=?2")
    // List<Route>findByDepartureAndArrivalAirportId(int departureAirportId, int arrivalAirportId);
}
