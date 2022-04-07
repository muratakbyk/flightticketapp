package flight.flightticketapp.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="flight")
public class Flight {

    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;

    @Column(name="flight_date",nullable = false)
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name="flight_seat",nullable = false)
    private int flightSeat;

    @Column(name="flight_price",nullable = false)
    private double flightPrice;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name="flight_airline_company",referencedColumnName = "id",nullable = false)
    private AirlineCompany flightAirlineCompany;


    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "flight_route",referencedColumnName = "id",nullable = false)
    private Route route;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "flight")
    @JsonIgnore
    private List<FlightTicket> flightTickets;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AirlineCompany getFlightAirlineCompany() {
        return flightAirlineCompany;
    }

    public void setFlightAirlineCompany(AirlineCompany flightAirlineCompany) {
        this.flightAirlineCompany = flightAirlineCompany;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public List<FlightTicket> getFlightTickets() {
        return flightTickets;
    }

    public void setFlightTickets(List<FlightTicket> flightTickets) {
        this.flightTickets = flightTickets;
    }

    public int getFlightSeat() {
        return flightSeat;
    }

    public void setFlightSeat(int flightSeat) {
        this.flightSeat = flightSeat;
    }

    public double getFlightPrice() {
        return flightPrice;
    }

    public void setFlightPrice(double flightPrice) {
        this.flightPrice = flightPrice;
    }

    public int getId() {
        return id;
    }
}
