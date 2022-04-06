package flight.flightticketapp.repo;


import flight.flightticketapp.entities.SellFlightTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellFlightTicketRepository extends JpaRepository<SellFlightTicket,Integer> {

}
