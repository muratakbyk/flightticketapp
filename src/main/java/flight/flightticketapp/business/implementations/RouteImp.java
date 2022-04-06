package flight.flightticketapp.business.implementations;

import flight.flightticketapp.business.abstracts.RouteAbstract;
import flight.flightticketapp.core.messages.Messages;
import flight.flightticketapp.core.rules.BusinessRules;
import flight.flightticketapp.core.utilities.*;
import flight.flightticketapp.entities.Airport;
import flight.flightticketapp.entities.Route;
import flight.flightticketapp.repo.AirportRepository;
import flight.flightticketapp.repo.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RouteImp implements RouteAbstract {
    @Autowired
    RouteRepository routeRepository;
    @Autowired
    AirportRepository airportRepository;
    @Override
    public Result add(Route route) {
        routeRepository.save(route);
        return new SuccessResult(Messages.routeCreated);
    }


    @Override
    public Result delete(int id) {
        routeRepository.deleteById(id);
        return new SuccessResult(Messages.routeDeleted);
    }

    @Override
    public DataResult<Route> getById(int id) {
        return new SuccessDataResult<Route>(routeRepository.findById(id).get());
    }

    @Override
    public DataResult<List<Route>> findByArrivalAirportId(int arrivalAirportId) {
        return new SuccessDataResult<List<Route>>
                (routeRepository.findByArrivalAirportId(arrivalAirportId));
    }

    @Override
    public DataResult<List<Route>> findByDepartureAirportId(int departureAirportId) {
        return new SuccessDataResult<List<Route>>(routeRepository.findByDepartureAirportId(departureAirportId));
    }

    @Override
    public DataResult<List<Route>> findByDepartureAndArrivalAirportId(int departureAirportId, int arrivalAirportId) {
        return new SuccessDataResult<List<Route>>(routeRepository.
                findByDepartureAirportIdAndAndArrivalAirportId(departureAirportId,arrivalAirportId));
    }

    @Override
    public Result createRoute(int departureAirportId, int arrivalAirportId) {
        if(!(BusinessRules.checkRouteAdd(departureAirportId,arrivalAirportId))){
            return new ErrorResult(Messages.airportCantBeSame);
        }
        Route route = new Route();
        route.setDepartureAirport(airportRepository.getById(departureAirportId));
        route.setArrivalAirport(airportRepository.getById(arrivalAirportId));
        return add(route);
    }




}
