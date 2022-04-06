package flight.flightticketapp.controllers;

import flight.flightticketapp.business.abstracts.FlightTicketAbstract;
import flight.flightticketapp.core.utilities.DataResult;
import flight.flightticketapp.core.utilities.Result;
import flight.flightticketapp.entities.Flight;
import flight.flightticketapp.entities.FlightTicket;
import flight.flightticketapp.entities.SellFlightTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
@RestController
@RequestMapping(value = "flightTicket")
public class FlightTicketController {

    @Autowired
    FlightTicketAbstract flightTicketAbstract;

    @PostMapping("/createFlightTicket")
    public Result createFlightTicket(@RequestParam int flightId, @RequestBody SellFlightTicket sellFlightTicket) {
        return flightTicketAbstract.createFlightTicket(flightId, sellFlightTicket);
    }

    @GetMapping("/flightTicketGetById")
    public DataResult<FlightTicket> getFlightById(@RequestParam int id) {
        return flightTicketAbstract.getById(id);
    }

    @PostMapping("/deleteFlightTicket")
    public Result deleteFlightTicket(@RequestParam int flightTicketId){
        return flightTicketAbstract.delete(flightTicketId);
    }




}
