package flight.flightticketapp.business.abstracts;

import flight.flightticketapp.core.utilities.DataResult;
import flight.flightticketapp.core.utilities.Result;
import flight.flightticketapp.entities.AirlineCompany;
import flight.flightticketapp.entities.Airport;

import java.util.List;

public interface AirlineCompanyAbstract extends BaseEntityAbstract<AirlineCompany> {
    Result createAirlineCompany(String name);
    DataResult<AirlineCompany> getById(int id);
    DataResult<List<AirlineCompany>> getAirlineCompaniesByName (String name);
    DataResult<List<AirlineCompany>> getAllAirlineCompanies();

}
