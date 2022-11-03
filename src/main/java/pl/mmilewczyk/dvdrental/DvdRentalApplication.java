package pl.mmilewczyk.dvdrental;

import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.mmilewczyk.dvdrental.command.exception.MovieEventsErrorHandler;

@SpringBootApplication
public class DvdRentalApplication {

    public static void main(String[] args) {
        SpringApplication.run(DvdRentalApplication.class, args);
    }

    @Autowired
    public void configure(EventProcessingConfigurer configurer) {
        configurer.registerListenerInvocationErrorHandler(
                "movie",
                configuration -> new MovieEventsErrorHandler()
        );
    }

}
