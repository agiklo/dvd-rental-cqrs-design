package pl.mmilewczyk.dvdrental.command.commands;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

import static java.util.UUID.*;

@Data
public class CreateMovieCommand {

    @TargetAggregateIdentifier
    private final String movieId;
    private final String title;
    private final BigDecimal price;

    public CreateMovieCommand(String title, BigDecimal price) {
        this.movieId = randomUUID().toString();
        this.title = title;
        this.price = price;
    }
}
