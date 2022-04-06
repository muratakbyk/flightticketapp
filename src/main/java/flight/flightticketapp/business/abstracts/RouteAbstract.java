package flight.flightticketapp.business.abstracts;

import flight.flightticketapp.core.utilities.DataResult;
import flight.flightticketapp.core.utilities.Result;
import flight.flightticketapp.entities.Route;

import java.util.List;

public interface RouteAbstract extends BaseEntityAbstract<Route> {

    DataResult<List<Route>> findByArrivalAirportId(int arrivalAirportId);
    DataResult<List<Route>> findByDepartureAirportId(int departureAirportId);
    DataResult<List<Route>> findByDepartureAndArrivalAirportId(int departureAirportId, int arrivalAirportId);
    Result createRoute(int departureAirportId, int arrivalAirportId);


}
