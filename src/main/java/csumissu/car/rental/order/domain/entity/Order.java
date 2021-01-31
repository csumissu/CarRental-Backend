package csumissu.car.rental.order.domain.entity;

import csumissu.car.rental.common.data.BaseDomain;
import csumissu.car.rental.order.domain.command.CreateOrderCommand;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;

import static csumissu.car.rental.common.Constants.BASE_ROUNDING_MODE;
import static csumissu.car.rental.common.Constants.BASE_SCALE;

@Getter
@SuperBuilder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Order extends BaseDomain {

    private long id;

    private long userId;

    private long carId;

    private Instant bookedAt;

    private Instant returnedAt;

    private BigDecimal dailyRentPrice;

    private int bookedDays;

    private BigDecimal totalPrice;

    public static Order create(CreateOrderCommand command) {
        command.validate();

        var order = Order.builder()
                .userId(command.getUserId())
                .carId(command.getCarId())
                .dailyRentPrice(command.getDailyRentPrice().setScale(BASE_SCALE, BASE_ROUNDING_MODE))
                .bookedDays(command.getBookedDays())
                .bookedAt(Instant.now())
                .build();
        order.recalculateTotalPrice();

        return order;
    }

    public void close() {
        if (returnedAt == null) {
            returnedAt = Instant.now();
        }
    }

    public void setId(long id) {
        this.id = id;
    }

    private void recalculateTotalPrice() {
        this.totalPrice = dailyRentPrice.multiply(new BigDecimal(bookedDays))
                .setScale(BASE_SCALE, BASE_ROUNDING_MODE);
    }

}
