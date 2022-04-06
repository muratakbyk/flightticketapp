package flight.flightticketapp.controllers;

import flight.flightticketapp.business.abstracts.AirlineCompanyAbstract;
import flight.flightticketapp.core.utilities.DataResult;
import flight.flightticketapp.core.utilities.Result;
import flight.flightticketapp.entities.AirlineCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "airlinecompany")
public class AirlineCompanyController {

    @Autowired
    AirlineCompanyAbstract airlineCompanyAbstract;

    @PostMapping("/airlineCreate")
    public Result createAirlineCompany(@RequestParam  String name){
        return airlineCompanyAbstract.createAirlineCompany(name);
    }

    @PostMapping("/airlineDelete")
    public Result deleteAirlineCompany (@RequestParam int id){
        return airlineCompanyAbstract.delete(id);
    }


    @GetMapping("/airlineGetByName")
    public DataResult<List<AirlineCompany>> getAirlineCompanyByName(@RequestParam String name){
        return airlineCompanyAbstract.getAirlineCompaniesByName(name);
    }

    @GetMapping("/airlineGetById")
    public DataResult<AirlineCompany> getAirlineCompanyById(@RequestParam int id){
        return airlineCompanyAbstract.getById(id);
    }

    @GetMapping("/allAirlineCompanies")
    public DataResult<List<AirlineCompany>> getAllAirlineCompanies(){
        return airlineCompanyAbstract.getAllAirlineCompanies();
    }
}
