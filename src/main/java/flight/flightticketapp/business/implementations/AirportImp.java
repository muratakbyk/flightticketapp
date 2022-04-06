package flight.flightticketapp.business.implementations;

import flight.flightticketapp.business.abstracts.AirportAbstract;
import flight.flightticketapp.core.messages.Messages;
import flight.flightticketapp.core.rules.BusinessRules;
import flight.flightticketapp.core.utilities.*;
import flight.flightticketapp.entities.Airport;
import flight.flightticketapp.repo.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AirportImp implements AirportAbstract {

    @Autowired
    AirportRepository airportRepository;

    @Override
    public Result add(Airport airport) {
        airportRepository.save(airport);
        return new SuccessResult(Messages.airportCreated);
    }

    @Override
    public Result delete(int id) {
        airportRepository.deleteById(id);
        return new SuccessResult(Messages.airportDeleted);
    }

    @Override
    public DataResult<Airport> getById(int id) {
        return new SuccessDataResult<Airport>(airportRepository.findById(id).get());
    }


    @Override
    public Result createAirport(String name, String city, String country) {
        if(!(BusinessRules.checkIfNameNull(name))){
            return new ErrorResult(Messages.airportNameCanNotBeNull);
        }
        Airport airport = new Airport();
        airport.setName(name);
        airport.setCity(city);
        airport.setCountry(country);
        return add(airport);
    }

    @Override
    public DataResult<List<Airport>> getAirports() {
        return new SuccessDataResult<List<Airport>>(airportRepository.findAll());

    }

    @Override
    public DataResult<List<Airport>> getAirportByAirportName(String name) {
        return new SuccessDataResult<List<Airport>>(airportRepository.findByName(name),Messages.airportsListed);
    }



}
