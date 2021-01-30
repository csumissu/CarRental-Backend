package csumissu.car.rental.car.domain.entity;

import csumissu.car.rental.car.domain.command.CreateCarCommand;
import csumissu.car.rental.common.data.BaseDomain;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static csumissu.car.rental.common.Constants.BASE_ROUNDING_MODE;
import static csumissu.car.rental.common.Constants.BASE_SCALE;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Car extends BaseDomain {

    private long id;

    private String model;

    private BigDecimal dailyRentPrice;

    private CarStatus status;

    public static Car create(CreateCarCommand command) {
        command.validate();

        return Car.builder()
                .model(command.getModel())
                .dailyRentPrice(command.getDailyRentPrice().setScale(BASE_SCALE, BASE_ROUNDING_MODE))
                .status(CarStatus.AVAILABLE)
                .build();
    }

    public void setId(long id) {
        this.id = id;
    }
}
