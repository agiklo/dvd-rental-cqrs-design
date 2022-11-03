package pl.mmilewczyk.dvdrental.query.controller;

import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mmilewczyk.dvdrental.query.model.MovieResponse;
import pl.mmilewczyk.dvdrental.query.queries.GetMoviesQuery;

import java.util.List;

import static org.axonframework.messaging.responsetypes.ResponseTypes.*;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieQueryController {

    private final QueryGateway queryGateway;

    @GetMapping
    public List<MovieResponse> getAllMovies() {
        GetMoviesQuery getMoviesQuery = new GetMoviesQuery();
        ResponseType<List<MovieResponse>> response = multipleInstancesOf(MovieResponse.class);
        return queryGateway.query(getMoviesQuery, response).join();
    }
}
