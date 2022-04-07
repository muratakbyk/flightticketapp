package flight.flightticketapp.business.implementations;

import flight.flightticketapp.business.abstracts.SellFlightTicketAbstract;
import flight.flightticketapp.core.messages.Messages;
import flight.flightticketapp.core.utilities.DataResult;
import flight.flightticketapp.core.utilities.Result;
import flight.flightticketapp.core.utilities.SuccessDataResult;
import flight.flightticketapp.core.utilities.SuccessResult;
import flight.flightticketapp.entities.SellFlightTicket;
import flight.flightticketapp.repo.SellFlightTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SellFlightTicketImp implements SellFlightTicketAbstract {

    @Autowired
    SellFlightTicketRepository sellFlightTicketRepository;

    @Override
    public Result add(SellFlightTicket sellFlightTicket) {
        sellFlightTicketRepository.save(sellFlightTicket);
        return new SuccessResult(Messages.sellFlightTicketCreated);
    }

    @Override
    public Result delete(int id) {
        return null;
    }

    @Override
    public DataResult<SellFlightTicket> getById(int id) {
        return new SuccessDataResult<SellFlightTicket>(sellFlightTicketRepository.findById(id).get());
    }
}
