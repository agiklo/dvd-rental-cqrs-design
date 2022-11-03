package pl.mmilewczyk.dvdrental.query.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public final class MovieResponse {
    private final String movieId;
    private final String title;
    private final BigDecimal price;
}
