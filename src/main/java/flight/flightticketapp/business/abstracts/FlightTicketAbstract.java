package flight.flightticketapp.business.abstracts;

import flight.flightticketapp.core.utilities.DataResult;
import flight.flightticketapp.core.utilities.Result;
import flight.flightticketapp.entities.Flight;
import flight.flightticketapp.entities.FlightTicket;
import flight.flightticketapp.entities.SellFlightTicket;

import java.util.Date;

public interface FlightTicketAbstract extends BaseEntityAbstract<FlightTicket> {

    Result createFlightTicket(int flightId, SellFlightTicket sellFlightTicket );
}
