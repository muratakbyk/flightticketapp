package flight.flightticketapp.business.abstracts;

import flight.flightticketapp.core.utilities.DataResult;
import flight.flightticketapp.core.utilities.Result;
import flight.flightticketapp.entities.Airport;

import java.util.List;

public interface AirportAbstract extends BaseEntityAbstract<Airport> {
    Result createAirport(String name, String city, String country);
    DataResult<List<Airport>> getAirports();
    DataResult<List<Airport>> getAirportByAirportName (String name);
}
