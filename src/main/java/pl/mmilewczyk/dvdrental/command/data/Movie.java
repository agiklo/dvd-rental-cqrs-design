package pl.mmilewczyk.dvdrental.command.data;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
public class Movie {

    @Id
    private String movieId;
    private String title;
    private BigDecimal price;

    public Movie(String movieId, String title, BigDecimal price) {
        validatePrice(price);
        this.movieId = movieId;
        this.title = title;
        this.price = price;
    }

    public BigDecimal changePrice(BigDecimal price) {
        validatePrice(price);
        this.price = price;
        return price;
    }

    private void validatePrice(BigDecimal price) {
        if (isPriceInvalid(price)) {
            throw new IllegalArgumentException("The price cannot be equals or less than zero");
        }
    }

    private boolean isPriceInvalid(BigDecimal price) {
        return price == null || price.intValue() < 0;
    }
}
