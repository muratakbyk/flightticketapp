package flight.flightticketapp.business.abstracts;

import flight.flightticketapp.core.utilities.DataResult;
import flight.flightticketapp.core.utilities.Result;
import flight.flightticketapp.entities.Flight;

import java.util.Date;
import java.util.List;

public interface FlightAbstract extends BaseEntityAbstract<Flight> {

    Result createFlight(int routeId, int airlinesCompanyId, int flightSeatCount, double flightPrice, Date date );
    DataResult<List<Flight>> getAllFlights();

}
