package csumissu.car.rental.car.domain.entity;

import csumissu.car.rental.car.domain.command.CreateCarCommand;
import csumissu.car.rental.car.exception.CarException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class CarTest {

    @Test
    public void should_create_car_when_command_is_valid() {
        // given
        var command = CreateCarCommand.builder()
                .model("any model")
                .dailyRentPrice(BigDecimal.ONE)
                .build();
        command = spy(command);

        // when
        var result = Car.create(command);

        // then
        verify(command).validate();

        assertNotNull(result);
        assertEquals(command.getModel(), result.getModel());
        assertEquals(new BigDecimal("1.00"), result.getDailyRentPrice());
        assertEquals(CarStatus.AVAILABLE, result.getStatus());
    }

    @Test
    public void should_mark_as_in_use_when_current_status_is_not_in_use() {
        // given
        var car = Car.builder()
                .status(CarStatus.AVAILABLE)
                .build();

        // when
        car.markAsInUse();

        // then
        assertEquals(CarStatus.IN_USE, car.getStatus());
    }

    @Test
    public void should_throw_already_in_use_exception_when_current_status_is_in_use() {
        // given
        var car = Car.builder()
                .status(CarStatus.IN_USE)
                .build();

        // when
        Executable action = car::markAsInUse;

        // then
        var exception = assertThrows(CarException.class, action);
        assertEquals(CarException.alreadyInUse(), exception);
    }

    @Test
    public void should_mark_as_available_when_current_status_is_not_available() {
        // given
        var car = Car.builder()
                .status(CarStatus.IN_USE)
                .build();

        // when
        car.markAsAvailable();

        // then
        assertEquals(CarStatus.AVAILABLE, car.getStatus());
    }

    @Test
    public void should_throw_already_available_exception_when_current_status_is_available() {
        // given
        var car = Car.builder()
                .status(CarStatus.AVAILABLE)
                .build();

        // when
        Executable action = car::markAsAvailable;

        // then
        var exception = assertThrows(CarException.class, action);
        assertEquals(CarException.alreadyAvailable(), exception);
    }

}
