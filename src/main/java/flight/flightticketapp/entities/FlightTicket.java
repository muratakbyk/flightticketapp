package flight.flightticketapp.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="flight_ticket")
public class FlightTicket {
    @Id
    @GeneratedValue
    private int id;

    @Column(name="date",nullable = false)
    private Date date;

    @Column(name="ticket_price",nullable = false)
    private double price;

    @Column(name="is_ticket_valid",nullable = false)
    private boolean valid;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name="flight",referencedColumnName = "id",nullable = false)
    private Flight flight;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name="flight_ticket_sell",referencedColumnName = "id",nullable = false)
    private SellFlightTicket flightTicketSell;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

     public SellFlightTicket getFlightTicketSell() {
        return flightTicketSell;
    }

    public void setFlightTicketSell(SellFlightTicket flightTicketSell) {
        this.flightTicketSell = flightTicketSell;
    }

    public int getId() {
        return id;
    }
}
