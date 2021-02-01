package csumissu.car.rental.car.domain.command;

import csumissu.car.rental.car.exception.CarException;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateCarCommandTest {

    @ParameterizedTest
    @MethodSource("invalidModels")
    public void should_throw_mode_invalid_exception_when_mode_is_incorrect(String model) {
        // given
        var command = CreateCarCommand.builder()
                .model(model)
                .dailyRentPrice(BigDecimal.ONE)
                .build();

        // when
        Executable action = command::validate;

        // then
        var exception = assertThrows(CarException.class, action);
        assertEquals(CarException.modelInvalid(), exception);
    }

    @ParameterizedTest
    @MethodSource("invalidDailyRentPrices")
    public void should_throw_daily_rent_price_invalid_exception_when_mode_is_incorrect(BigDecimal dailyRentPrice) {
        // given
        var command = CreateCarCommand.builder()
                .model("any model")
                .dailyRentPrice(dailyRentPrice)
                .build();

        // when
        Executable action = command::validate;

        // then
        var exception = assertThrows(CarException.class, action);
        assertEquals(CarException.dailyRentPriceInvalid(), exception);
    }

    private static Stream<String> invalidModels() {
        return Stream.of(null, "", " ");
    }

    private static Stream<BigDecimal> invalidDailyRentPrices() {
        return Stream.of(null, BigDecimal.ZERO);
    }

}
