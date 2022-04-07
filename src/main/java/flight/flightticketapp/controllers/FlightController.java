package flight.flightticketapp.controllers;

import flight.flightticketapp.business.abstracts.FlightAbstract;
import flight.flightticketapp.core.utilities.DataResult;
import flight.flightticketapp.core.utilities.Result;
import flight.flightticketapp.entities.Airport;
import flight.flightticketapp.entities.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@RestController
@RequestMapping(value = "flight")
public class FlightController {

    @Autowired
    FlightAbstract flightAbstract;

    @PostMapping("/createFlight")
    public Result createFlight(@RequestParam int routeId, @RequestParam int airlinesCompanyId,
                               @RequestParam int flightSeatCount, @RequestParam double flightPrice,@RequestParam  Date date) {
        return flightAbstract.createFlight(routeId, airlinesCompanyId, flightSeatCount, flightPrice, date);
    }

    @PostMapping("/flightDelete")
    public Result flightDelete (@RequestParam int id){
        return flightAbstract.delete(id);
    }

    @GetMapping("/flightGetById")
    public DataResult<Flight> getFlightById(@RequestParam int id) {
        return flightAbstract.getById(id);
    }

    @GetMapping("/flightGetAll")
    public DataResult<List<Flight>> getAllFlights(){
        return flightAbstract.getAllFlights();
    }


}