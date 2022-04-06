package flight.flightticketapp.business.abstracts;

import flight.flightticketapp.core.utilities.DataResult;
import flight.flightticketapp.entities.SellFlightTicket;


public interface SellFlightTicketAbstract extends BaseEntityAbstract<SellFlightTicket> {

    DataResult<SellFlightTicket> getById(int id);

}
