package flight.flightticketapp.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="airline_company")
public class AirlineCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="airline_company_name",nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "flightAirlineCompany")
    @JsonIgnore
    private List<Flight> flightList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Flight> getFlightList() {
        return flightList;
    }

   public void setFlightList(List<Flight> flightList) {
        this.flightList = flightList;
    }

    public int getId() {
        return id;
    }
}
