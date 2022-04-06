package flight.flightticketapp.core.rules;


import flight.flightticketapp.core.utilities.ErrorResult;

import java.math.BigDecimal;
import java.util.Date;

public class  BusinessRules {


    //We calculate the final ticket price. Every time 10% of the seats on the plane are sold,
    //we increase the ticket price by 10%
    public static double priceCalculate(int flightSeatCount, int flightTicketCount, double flightTicketPrice) {
        double percentRate = 0.10;
        double priceRate = 0.10;
        int seatCount = flightSeatCount;
        double ticketCount = flightTicketCount;
        double flightPrice = flightTicketPrice;
        double rate = seatCount * percentRate;
        double countPercent = ticketCount / rate;
        if (countPercent >= 1) {
            countPercent = new BigDecimal(String.valueOf(countPercent)).intValue();
            for(int i =0; i<countPercent;i++){
                flightPrice += flightPrice * priceRate;
            }
            return flightPrice;
        }
        return flightPrice;
    }

    //To sell new tickets, we check if all seats on the flight are sold or not
    public static boolean checkIfAvailableTicket(int flightSeatCount, int flightTicketCount){
        int seatCount = flightSeatCount;
        if(seatCount -(flightTicketCount + 1) >=0){
            return true;
        }
        return false;
    }

    //We mask credit card number to save it in the db
    public static String maskCardNumber (String string) {
        string = string.replaceAll("-", "");
        string = string.replaceAll(",", "");
        return string.replaceAll("\\b([0-9]{6})[0-9]{6}([0-9]{4})\\b", "$1******$2");
    }

    //We check the flight date has passed or not to set the ticket valid or invalid
    public static boolean checkDate (Date date){
        Date today = new Date();
        if(date.getTime()>today.getTime()){
            return true;
        }

        return false;
    }

    //Before adding a route, we check if the departure and arrival airport are the same if they are same
    //we will return false
    public static boolean checkRouteAdd(int departureAirportId, int arrivalAirportId){
        if(departureAirportId == arrivalAirportId){
            return false;
        }
        return true;
    }

    public static boolean checkIfNameNull(String name){
        if(name == null){
            return false;
        }
        return true;
    }

    //We check the list size if it is zero or not.
   /* public static boolean checkListSizeIfZero(int checkNumber){
        if(checkNumber>0)
            return true;
    return false;
    }*/
}
