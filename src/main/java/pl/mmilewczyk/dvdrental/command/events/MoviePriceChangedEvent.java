package pl.mmilewczyk.dvdrental.command.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MoviePriceChangedEvent {

    private String movieId;
    private BigDecimal price;
}
