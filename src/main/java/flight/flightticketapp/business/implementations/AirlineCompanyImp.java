package flight.flightticketapp.business.implementations;

import flight.flightticketapp.business.abstracts.AirlineCompanyAbstract;
import flight.flightticketapp.core.messages.Messages;
import flight.flightticketapp.core.rules.BusinessRules;
import flight.flightticketapp.core.utilities.*;
import flight.flightticketapp.entities.AirlineCompany;
import flight.flightticketapp.repo.AirlineCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class AirlineCompanyImp implements AirlineCompanyAbstract {

    @Autowired
    AirlineCompanyRepository airlineCompanyRepository;

    @Override
    public Result add(AirlineCompany airlineCompany) {

        airlineCompanyRepository.save(airlineCompany);
        return new SuccessResult(Messages.airlineCompanyAdded);
    }

    @Override
    public Result delete(int id) {
        airlineCompanyRepository.deleteById(id);
        return new SuccessResult(Messages.airlineCompanyDeleted);
    }


    @Override
    public Result createAirlineCompany(String name) {
        if(!(BusinessRules.checkIfNameNull(name))){
            return new ErrorResult(Messages.airlineCompanyNameCanNotBeNull);
        }
        AirlineCompany airlineCompany = new AirlineCompany();
        airlineCompany.setName(name);
        return add(airlineCompany);
    }

    @Override
    public DataResult<AirlineCompany> getById(int id)  {
        return new SuccessDataResult<AirlineCompany>
                (airlineCompanyRepository.findById(id).get());
    }

    @Override
    public DataResult<List<AirlineCompany>> getAirlineCompaniesByName(String name) {
        return new SuccessDataResult<List<AirlineCompany>>(airlineCompanyRepository.findByName(name),
                Messages.airlineCompaniesListed);
    }

    @Override
    public DataResult<List<AirlineCompany>> getAllAirlineCompanies() {
        return new SuccessDataResult<List<AirlineCompany>>(airlineCompanyRepository.findAll(),Messages.airlineCompaniesListed);
    }


}
