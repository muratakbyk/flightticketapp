package flight.flightticketapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication


public class FlightticketappApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightticketappApplication.class, args);
	}

}
