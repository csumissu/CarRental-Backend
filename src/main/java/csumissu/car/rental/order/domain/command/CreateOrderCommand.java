package csumissu.car.rental.order.domain.command;

import csumissu.car.rental.car.exception.CarException;
import csumissu.car.rental.order.exception.OrderException;
import csumissu.car.rental.user.exception.UserException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderCommand {

    private Long userId;

    private Long carId;

    private BigDecimal dailyRentPrice;

    private Integer bookedDays;

    public void validate() {
        if (userId == null || userId <= 0L) {
            throw UserException.notFound();
        }

        if (carId == null || carId <= 0L) {
            throw CarException.notFound();
        }

        if (dailyRentPrice == null || dailyRentPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw CarException.dailyRentPriceInvalid();
        }

        if (bookedDays == null || bookedDays < 1) {
            throw OrderException.bookedDaysInvalid();
        }
    }

}
