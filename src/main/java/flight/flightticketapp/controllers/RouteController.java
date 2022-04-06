package flight.flightticketapp.controllers;

import flight.flightticketapp.business.abstracts.RouteAbstract;
import flight.flightticketapp.core.utilities.DataResult;
import flight.flightticketapp.core.utilities.Result;
import flight.flightticketapp.entities.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "route")
public class RouteController {

    @Autowired
    RouteAbstract routeAbstract;

    @PostMapping("/createRoute")
    public Result createRoute(@RequestParam int departureId, @RequestParam int arrivalId) {
        return routeAbstract.createRoute(departureId,arrivalId);
    }
    @PostMapping("/deleteRoute")
    public Result deleteRoute (@RequestParam int id){
        return routeAbstract.delete(id);
    }

    @GetMapping("/routeGetById")
    public DataResult<Route> getRouteById(@RequestParam int id) {
        return routeAbstract.getById(id);
    }

    @GetMapping("/getRouteByDepartureAirportId")
    public DataResult<List<Route>> getRouteByDepartureAirportId (@RequestParam int departureAirportId){
        return routeAbstract.findByDepartureAirportId(departureAirportId);
    }
    @GetMapping("/getRouteByArrivalAirportId")
    public DataResult<List<Route>> getRouteByArrivalAirportId(@RequestParam int arrivalAirportId){
        return routeAbstract.findByArrivalAirportId(arrivalAirportId);
    }

    @GetMapping("/getRouteByDepartureAndArrivalAirportId")
    public DataResult<List<Route>> getRouteByDepartureAndArrivalAirportId(@RequestParam int departureAirportId,
                                                                          @RequestParam int arrivalAirportId){
        return routeAbstract.findByDepartureAndArrivalAirportId(departureAirportId,arrivalAirportId);
    }


}
