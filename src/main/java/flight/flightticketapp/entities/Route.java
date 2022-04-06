package flight.flightticketapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="route")
public class Route {
    @Id
    @GeneratedValue
    @Column(name="id")

    private int id;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name="arrival_airport",referencedColumnName = "id",nullable = false)
   /* @OneToOne
    @JoinColumn(name = "arrivalAirport",referencedColumnName = "id")*/
    private Airport arrivalAirport;


    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name="departure_airport",referencedColumnName = "id",nullable = false)
    private Airport departureAirport;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "route")
    @JsonIgnore
    private List<Flight> flightRecords;


    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public List<Flight> getFlightRecords() {
        return flightRecords;
    }

    public void setFlightRecords(List<Flight> flightRecords) {
        this.flightRecords = flightRecords;
    }

    public int getId() {
        return id;
    }
}
