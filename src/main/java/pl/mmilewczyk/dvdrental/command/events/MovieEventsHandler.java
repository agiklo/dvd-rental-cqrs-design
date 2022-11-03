package pl.mmilewczyk.dvdrental.command.events;

import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.stereotype.Component;
import pl.mmilewczyk.dvdrental.command.data.Movie;
import pl.mmilewczyk.dvdrental.command.data.MovieRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@ProcessingGroup("movie")
public class MovieEventsHandler {

    private final MovieRepository movieRepository;

    @EventHandler
    public void on(MovieCreatedEvent movieCreatedEvent) {
        Movie movie = new Movie(
                movieCreatedEvent.getMovieId(),
                movieCreatedEvent.getTitle(),
                movieCreatedEvent.getPrice());
        movieRepository.save(movie);
    }

    @EventHandler
    public void on(MoviePriceChangedEvent moviePriceChangedEvent) {
        Optional<Movie> movie = movieRepository.findById(moviePriceChangedEvent.getMovieId());
        movie.ifPresent(value -> {
            value.changePrice(value.changePrice(moviePriceChangedEvent.getPrice()));
            movieRepository.save(value);
        });
    }

    @EventHandler
    public void on(MovieDeletedEvent movieDeletedEvent) {
        movieRepository.deleteById(movieDeletedEvent.getMovieId());
    }

    @ExceptionHandler
    public void handle(Exception exception) throws Exception {
        throw exception;
    }
}
