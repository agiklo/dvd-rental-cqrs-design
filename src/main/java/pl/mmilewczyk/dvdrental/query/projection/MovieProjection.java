package pl.mmilewczyk.dvdrental.query.projection;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import pl.mmilewczyk.dvdrental.command.data.Movie;
import pl.mmilewczyk.dvdrental.command.data.MovieRepository;
import pl.mmilewczyk.dvdrental.query.model.MovieResponse;
import pl.mmilewczyk.dvdrental.query.queries.GetMoviesQuery;

import java.util.List;

@Component
public record MovieProjection(MovieRepository movieRepository) {

    @QueryHandler
    public List<MovieResponse> handle(GetMoviesQuery getMoviesQuery) {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(this::mapMovieToResponse)
                .toList();
    }

    private MovieResponse mapMovieToResponse(Movie movie) {
        return MovieResponse.builder()
                .movieId(movie.getMovieId())
                .title(movie.getTitle())
                .price(movie.getPrice())
                .build();
    }
}
