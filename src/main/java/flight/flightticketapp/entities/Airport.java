package flight.flightticketapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="airport")
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="airport_name",nullable = false)
    private String name;

    @Column(name="airport_city")
    private String city;

    @Column(name="airport_country")
    private String country;

   @OneToMany(cascade = CascadeType.ALL,mappedBy = "arrivalAirport")
   @JsonIgnore
    private List<Route> arrivalRoute;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "departureAirport")
    @JsonIgnore
    private List<Route> departureRoute;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Route> getArrivalRoute() {
        return arrivalRoute;
    }

    public void setArrivalRoute(List<Route> arrivalRoute) {
        this.arrivalRoute = arrivalRoute;
    }

    public List<Route> getDepartureRoute() {
        return departureRoute;
    }

    public void setDepartureRoute(List<Route> departureRoute) {
        this.departureRoute = departureRoute;
    }

    public int getId() {
        return id;
    }
}
