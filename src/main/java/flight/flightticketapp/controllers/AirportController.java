package flight.flightticketapp.controllers;

import flight.flightticketapp.business.abstracts.AirportAbstract;
import flight.flightticketapp.core.utilities.DataResult;
import flight.flightticketapp.core.utilities.Result;
import flight.flightticketapp.entities.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "airport")
public class AirportController {

    @Autowired
    AirportAbstract airportAbstract;

    @PostMapping("/createAirport")
    public Result createAirport( String name,  String city, String country){
        return airportAbstract.createAirport(name, city, country);
    }
    @GetMapping("/airportGetByName")
    public DataResult<List<Airport>>  getAirportByName(@RequestParam String name){
        return airportAbstract.getAirportByAirportName(name);
    }

    @GetMapping("/airportGetById")
    public DataResult<Airport> getAirportById(@RequestParam int id){
        return airportAbstract.getById(id);
    }

    @PostMapping("/deleteAirport")
    public Result deleteAirport(int id){
        return airportAbstract.delete(id);
    }

    @GetMapping("/getAllAirports")
    public DataResult<List<Airport>> getAllAirports(){
        return airportAbstract.getAirports();
    }
}
