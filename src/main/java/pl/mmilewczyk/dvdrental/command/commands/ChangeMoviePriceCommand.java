package pl.mmilewczyk.dvdrental.command.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public final class ChangeMoviePriceCommand {

    @TargetAggregateIdentifier
    private String movieId;
    private BigDecimal price;
}
