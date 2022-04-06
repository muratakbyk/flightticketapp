package flight.flightticketapp.business.implementations;

import flight.flightticketapp.business.abstracts.FlightTicketAbstract;
import flight.flightticketapp.business.abstracts.SellFlightTicketAbstract;
import flight.flightticketapp.core.messages.Messages;
import flight.flightticketapp.core.rules.BusinessRules;
import flight.flightticketapp.core.utilities.*;
import flight.flightticketapp.entities.FlightTicket;
import flight.flightticketapp.entities.SellFlightTicket;
import flight.flightticketapp.repo.FlightRepository;
import flight.flightticketapp.repo.FlightTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlightTicketImp implements FlightTicketAbstract {

    @Autowired
    FlightTicketRepository flightTicketRepository;

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    SellFlightTicketAbstract sellFlightTicketAbstract;


    @Override
    public Result add(FlightTicket flightTicket) {
        flightTicketRepository.save(flightTicket);
        return new SuccessResult(Messages.flightTicketCreated);
    }

    @Override
    public Result delete(int id) {
        flightTicketRepository.deleteById(id);
        return new SuccessResult(Messages.flightTicketDeleted);
    }

    @Override
    public DataResult<FlightTicket> getById(int id) {
        return new SuccessDataResult<FlightTicket>(flightTicketRepository.getById(id));
    }

    @Override
    public Result createFlightTicket(int flightId, SellFlightTicket sellFlightTicket) {
        if(!(BusinessRules.checkIfAvailableTicket(flightRepository.getById(flightId).getFlightSeat(),
                flightRepository.getById(flightId).getFlightTickets().size()))){
            return new ErrorResult(Messages.flightNotAvailable);
        }
        SellFlightTicket sellingFlightTicket = new SellFlightTicket();
        sellingFlightTicket.setCardNumber(
                BusinessRules.maskCardNumber(sellFlightTicket.getCardNumber()));
        sellingFlightTicket.setCardCvv(sellFlightTicket.getCardCvv());
        sellingFlightTicket.setCardName(sellFlightTicket.getCardName());
        sellingFlightTicket.setCardDay(sellFlightTicket.getCardDay());
        sellingFlightTicket.setCardMonth(sellFlightTicket.getCardMonth());
        sellingFlightTicket.setCardYear(sellFlightTicket.getCardYear());
        sellFlightTicketAbstract.add(sellingFlightTicket);

        FlightTicket flightTicket = new FlightTicket();
        flightTicket.setFlight(flightRepository.getById(flightId));
        flightTicket.setDate(flightRepository.getById(flightId).getDate());
        flightTicket.setPrice(BusinessRules.priceCalculate(
                flightRepository.getById(flightId).getFlightSeat(),
                flightRepository.getById(flightId).getFlightTickets().size(),
                flightRepository.getById(flightId).getFlightPrice()));
        flightTicket.setValid(BusinessRules.checkDate(
                flightTicket.getDate()));
        flightTicket.setFlightTicketSell(sellingFlightTicket);
       // add(flightTicket);
        return add(flightTicket);
    }




}
