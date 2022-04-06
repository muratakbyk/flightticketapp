package flight.flightticketapp.core;

import flight.flightticketapp.business.abstracts.FlightTicketAbstract;
import flight.flightticketapp.core.rules.BusinessRules;
import flight.flightticketapp.entities.*;
import flight.flightticketapp.repo.*;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class BusinessRulesTest {
    double flightPrice = 200;
    int flightSeatCount = 20;
    int flightTicketCount = 0;
    int routeId1 = 1;
    int routeId2 = 2;
    String name = "testname";
    String date1 = "2022-09-11 12:10:40";
    String date2 = "2021-10-04 12:10:40";


    @BeforeEach
    void setUp() {

    }

    @Test
    void priceCalculatorMethodTest(){
        assertEquals(BusinessRules.priceCalculate(flightSeatCount,flightTicketCount,flightPrice),200);
        assertEquals((int)BusinessRules.priceCalculate(flightSeatCount,flightTicketCount+1,flightPrice),200);
        assertEquals((int)BusinessRules.priceCalculate(flightSeatCount,flightTicketCount+2,flightPrice),220);
        assertEquals((int)BusinessRules.priceCalculate(flightSeatCount,flightTicketCount+3,flightPrice),220);
        assertEquals((int)BusinessRules.priceCalculate(flightSeatCount,flightTicketCount+4,flightPrice),242);
        assertEquals((int)BusinessRules.priceCalculate(flightSeatCount,flightTicketCount+5,flightPrice),242);
        assertEquals((int)BusinessRules.priceCalculate(flightSeatCount,flightTicketCount+6,flightPrice),266);
        assertEquals((int)BusinessRules.priceCalculate(flightSeatCount,flightTicketCount+7,flightPrice),266);
        assertEquals((int)BusinessRules.priceCalculate(flightSeatCount,flightTicketCount+8,flightPrice),292);
        assertEquals((int)BusinessRules.priceCalculate(flightSeatCount,flightTicketCount+9,flightPrice),292);
        assertEquals((int)BusinessRules.priceCalculate(flightSeatCount,flightTicketCount+10,flightPrice),322);
        assertEquals((int)BusinessRules.priceCalculate(flightSeatCount,flightTicketCount+11,flightPrice),322);
        assertEquals((int)BusinessRules.priceCalculate(flightSeatCount,flightTicketCount+12,flightPrice),354);
        assertEquals((int)BusinessRules.priceCalculate(flightSeatCount,flightTicketCount+13,flightPrice),354);
        assertEquals((int)BusinessRules.priceCalculate(flightSeatCount,flightTicketCount+14,flightPrice),389);
        assertEquals((int)BusinessRules.priceCalculate(flightSeatCount,flightTicketCount+15,flightPrice),389);
        assertEquals((int)BusinessRules.priceCalculate(flightSeatCount,flightTicketCount+16,flightPrice),428);
        assertEquals((int)BusinessRules.priceCalculate(flightSeatCount,flightTicketCount+17,flightPrice),428);
        assertEquals((int)BusinessRules.priceCalculate(flightSeatCount,flightTicketCount+18,flightPrice),471);
        assertEquals((int)BusinessRules.priceCalculate(flightSeatCount,flightTicketCount+19,flightPrice),471);

    }

    @Test
    void maskCardNumberTest(){
        assertEquals("123456******4567",BusinessRules.maskCardNumber("1234567891234567"));
        assertEquals("585614******3439",BusinessRules.maskCardNumber("5856-1415-6545-3439"));
        assertEquals("143621******3456",BusinessRules.maskCardNumber("1436,2139,9886,3456"));
    }

    @Test
    void checkIfAvailableTicketTest(){
        assertEquals(BusinessRules.checkIfAvailableTicket(flightSeatCount,flightTicketCount),true);
        assertEquals(BusinessRules.checkIfAvailableTicket(flightSeatCount,flightTicketCount+20),false);
    }
    @Test
    void checkDateTest() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        formatter.setTimeZone(TimeZone.getTimeZone("Turkey"));
        Date futureDate = formatter.parse(date1);
        Date passedDate = formatter.parse(date2);
        assertEquals(BusinessRules.checkDate(futureDate),true);
        assertEquals(BusinessRules.checkDate(passedDate),false);
    }
    @Test
    void checkRouteAddTest(){
        assertEquals(BusinessRules.checkRouteAdd(routeId1,routeId1),false);
        assertEquals(BusinessRules.checkRouteAdd(routeId1,routeId2),true);

    }
    @Test
    void checkIfNameNullTest(){
        assertEquals(BusinessRules.checkIfNameNull(null),false);
        assertEquals(BusinessRules.checkIfNameNull(name),true);
    }
}
