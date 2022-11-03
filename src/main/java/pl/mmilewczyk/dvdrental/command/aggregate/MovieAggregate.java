package pl.mmilewczyk.dvdrental.command.aggregate;

import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import pl.mmilewczyk.dvdrental.command.commands.ChangeMoviePriceCommand;
import pl.mmilewczyk.dvdrental.command.commands.CreateMovieCommand;
import pl.mmilewczyk.dvdrental.command.commands.DeleteMovieCommand;
import pl.mmilewczyk.dvdrental.command.events.MovieCreatedEvent;
import pl.mmilewczyk.dvdrental.command.events.MovieDeletedEvent;
import pl.mmilewczyk.dvdrental.command.events.MoviePriceChangedEvent;

import java.math.BigDecimal;

import static org.axonframework.modelling.command.AggregateLifecycle.*;
import static org.springframework.beans.BeanUtils.*;

@Aggregate
@NoArgsConstructor
public class MovieAggregate {

    @AggregateIdentifier
    private String movieId;
    private String title;
    private BigDecimal price;

    @CommandHandler
    public MovieAggregate(CreateMovieCommand command) {
        MovieCreatedEvent movieCreatedEvent = new MovieCreatedEvent();
        copyProperties(command, movieCreatedEvent);
        apply(movieCreatedEvent);
    }

    @EventSourcingHandler
    public void on(MovieCreatedEvent event) {
        this.movieId = event.getMovieId();
        this.title = event.getTitle();
        this.price = event.getPrice();
    }

    @CommandHandler
    public void changePriceOfMovie(ChangeMoviePriceCommand command) {
        MoviePriceChangedEvent moviePriceChangedEvent = new MoviePriceChangedEvent();
        copyProperties(command, moviePriceChangedEvent);
        apply(moviePriceChangedEvent);
    }

    @EventSourcingHandler
    public void on(MoviePriceChangedEvent event) {
        this.movieId = event.getMovieId();
        this.price = event.getPrice();
    }

    @CommandHandler
    public void deleteMovie(DeleteMovieCommand command) {
        apply(new MovieDeletedEvent(command.getMovieId()));
    }

    @EventSourcingHandler
    public void on(MovieDeletedEvent event) {
        this.movieId = event.getMovieId();
    }
}
