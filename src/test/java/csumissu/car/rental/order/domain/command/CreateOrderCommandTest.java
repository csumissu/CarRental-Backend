package csumissu.car.rental.order.domain.command;

import csumissu.car.rental.car.exception.CarException;
import csumissu.car.rental.order.exception.OrderException;
import csumissu.car.rental.user.exception.UserException;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateOrderCommandTest {

    @ParameterizedTest
    @MethodSource("invalidUserIds")
    public void should_throw_user_not_found_exception_when_user_id_is_incorrect(Long userId) {
        // given
        var command = createOrderCommand();
        command.setUserId(userId);

        // when
        Executable action = command::validate;

        // then
        var exception = assertThrows(UserException.class, action);
        assertEquals(UserException.notFound(), exception);
    }

    @ParameterizedTest
    @MethodSource("invalidCarIds")
    public void should_throw_car_not_found_exception_when_car_id_is_incorrect(Long carId) {
        // given
        var command = createOrderCommand();
        command.setCarId(carId);

        // when
        Executable action = command::validate;

        // then
        var exception = assertThrows(CarException.class, action);
        assertEquals(CarException.notFound(), exception);
    }

    @ParameterizedTest
    @MethodSource("invalidDailyRentPrices")
    public void should_throw_daily_rent_price_invalid_exception_when_daily_rent_price_is_incorrect(BigDecimal dailyRentPrice) {
        // given
        var command = createOrderCommand();
        command.setDailyRentPrice(dailyRentPrice);

        // when
        Executable action = command::validate;

        // then
        var exception = assertThrows(CarException.class, action);
        assertEquals(CarException.dailyRentPriceInvalid(), exception);
    }

    @ParameterizedTest
    @MethodSource("invalidBookedDays")
    public void should_throw_booked_days_invalid_exception_when_booked_days_is_incorrect(Integer bookedDays) {
        // given
        var command = createOrderCommand();
        command.setBookedDays(bookedDays);

        // when
        Executable action = command::validate;

        // then
        var exception = assertThrows(OrderException.class, action);
        assertEquals(OrderException.bookedDaysInvalid(), exception);
    }

    private static Stream<Long> invalidUserIds() {
        return Stream.of(null, 0L);
    }

    private static Stream<Long> invalidCarIds() {
        return Stream.of(null, 0L);
    }

    private static Stream<BigDecimal> invalidDailyRentPrices() {
        return Stream.of(null, BigDecimal.ZERO);
    }

    private static Stream<Integer> invalidBookedDays() {
        return Stream.of(null, 0);
    }

    private static CreateOrderCommand createOrderCommand() {
        return CreateOrderCommand.builder()
                .userId(1L)
                .carId(2L)
                .dailyRentPrice(BigDecimal.TEN)
                .bookedDays(3)
                .build();
    }
}
