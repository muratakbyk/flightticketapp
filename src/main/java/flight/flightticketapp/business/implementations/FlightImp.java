package flight.flightticketapp.business.implementations;

import flight.flightticketapp.business.abstracts.FlightAbstract;
import flight.flightticketapp.core.messages.Messages;
import flight.flightticketapp.core.utilities.DataResult;
import flight.flightticketapp.core.utilities.Result;
import flight.flightticketapp.core.utilities.SuccessDataResult;
import flight.flightticketapp.core.utilities.SuccessResult;
import flight.flightticketapp.entities.AirlineCompany;
import flight.flightticketapp.entities.Flight;
import flight.flightticketapp.entities.Route;
import flight.flightticketapp.repo.AirlineCompanyRepository;
import flight.flightticketapp.repo.FlightRepository;
import flight.flightticketapp.repo.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class FlightImp implements FlightAbstract {
    @Autowired
    FlightRepository flightRepository;

    @Autowired
    AirlineCompanyRepository airlineCompanyRepository;

    @Autowired
    RouteRepository routeRepository;

    @Override
    public Result add(Flight flight) {
        flightRepository.save(flight);
        return new SuccessResult(Messages.flightCreated);
    }

    @Override
    public Result delete(int id) {
        flightRepository.deleteById(id);
        return new SuccessResult(Messages.flightDeleted);
    }

    @Override
    public Result createFlight(int routeId, int airlinesCompanyId, int flightSeatCount, double flightPrice, Date date) {

        AirlineCompany airlineCompany = airlineCompanyRepository.getById(airlinesCompanyId);
        Flight flight = new Flight();
        flight.setFlightSeat(flightSeatCount);
        flight.setFlightPrice(flightPrice);
        flight.setDate(date);
        flight.setRoute(routeRepository.getById(routeId));
        flight.setFlightAirlineCompany(airlineCompany);
        return add(flight);
    }

    @Override
    public DataResult<Flight> getById(int id) {
        return new SuccessDataResult<Flight>
                (flightRepository.findById(id).get());
    }

    @Override
    public DataResult<List<Flight>> getAllFlights() {
        return new SuccessDataResult<List<Flight>>(flightRepository.findAll());
    }


}
