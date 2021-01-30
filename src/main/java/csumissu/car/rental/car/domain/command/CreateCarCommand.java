package csumissu.car.rental.car.domain.command;

import csumissu.car.rental.car.exception.CarException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarCommand {

    private String model;

    private BigDecimal dailyRentPrice;

    public void validate() {
        if (!StringUtils.hasText(model)) {
            throw CarException.modelInvalid();
        }

        if (dailyRentPrice == null || dailyRentPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw CarException.dailyRentPriceInvalid();
        }
    }

}
