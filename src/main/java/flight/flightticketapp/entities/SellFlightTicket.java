package flight.flightticketapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="sell_flight_ticket")
public class SellFlightTicket {

    @Id
    @GeneratedValue
    @Column(name="id",insertable = false)
    private int id;

    @Column(name="card_number",nullable = false,length = 16)
    private String cardNumber;

    @Column(name="card_cvv",nullable = false, length = 3)
    private String cardCvv;

    @Column(name="card_month",nullable = false,length = 2)
    private String cardMonth;

    @Column(name="card_year",nullable = false,length = 4)
    private String cardYear;

    @Column(name="card_name",nullable = false)
    private String cardName;

    @Column(name="card_day",nullable = false,length = 2)
    private String cardDay;



    @OneToMany(cascade = CascadeType.ALL,mappedBy = "flightTicketSell")
    @JsonIgnore
    private List<FlightTicket> flightTicketList;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardId) {
        this.cardNumber = cardId;
    }

    public String getCardCvv() {
        return cardCvv;
    }

    public void setCardCvv(String cardCvv) {
        this.cardCvv = cardCvv;
    }

    public String getCardDay() {
        return cardDay;
    }

    public void setCardDay(String cardDay) {
        this.cardDay = cardDay;
    }

    public String getCardMonth() {
        return cardMonth;
    }

    public void setCardMonth(String cardMonth) {
        this.cardMonth = cardMonth;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public List<FlightTicket> getFlightTicketList() {
        return flightTicketList;
    }

    public void setFlightTicketList(List<FlightTicket> flightTicketList) {
        this.flightTicketList = flightTicketList;
    }

    public int getId() {
        return id;
    }

    public String getCardYear() {
        return cardYear;
    }

    public void setCardYear(String cardYear) {
        this.cardYear = cardYear;
    }
}
