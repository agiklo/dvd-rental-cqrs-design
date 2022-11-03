package pl.mmilewczyk.dvdrental.command.controller;

import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;
import pl.mmilewczyk.dvdrental.command.commands.ChangeMoviePriceCommand;
import pl.mmilewczyk.dvdrental.command.commands.CreateMovieCommand;
import pl.mmilewczyk.dvdrental.command.commands.DeleteMovieCommand;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieCommandController {

    private final CommandGateway commandGateway;

    @PostMapping
    public String addMovie(@RequestBody CreateMovieCommand createMovieCommand) {
        return commandGateway.sendAndWait(createMovieCommand);
    }

    @PutMapping
    public String changePriceOfMovie(@RequestBody ChangeMoviePriceCommand changeMoviePriceCommand) {
        return commandGateway.sendAndWait(changeMoviePriceCommand);
    }

    @DeleteMapping
    public String deleteMovie(@RequestBody DeleteMovieCommand deleteMovieCommand) {
        return commandGateway.sendAndWait(deleteMovieCommand);
    }
}
