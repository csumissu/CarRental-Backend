package csumissu.car.rental.order.domain.entity;

import csumissu.car.rental.order.domain.command.CreateOrderCommand;
import csumissu.car.rental.order.exception.OrderException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class OrderTest {

    @Test
    public void should_create_order_when_command_is_valid() {
        // given
        var command = createOrderCommand();
        command = spy(command);

        // when
        var result = Order.create(command);

        // then
        verify(command).validate();

        assertNotNull(result);
        assertEquals(command.getUserId(), result.getUserId());
        assertEquals(command.getCarId(), result.getCarId());
        assertEquals(new BigDecimal("1.00"), result.getDailyRentPrice());
        assertEquals(command.getBookedDays(), result.getBookedDays());
        assertEquals(new BigDecimal("3.00"), result.getTotalPrice());
    }

    @Test
    public void should_close_order_when_returned_at_is_null() {
        // given
        var order = Order.builder()
                .build();

        // when
        order.close();

        // then
        assertNotNull(order.getReturnedAt());
    }

    @Test
    public void should_throw_already_closed_exception_when_returned_at_is_not_null() {
        // given
        var order = Order.builder()
                .returnedAt(Instant.now())
                .build();

        // when
        Executable action = order::close;

        // then
        var exception = assertThrows(OrderException.class, action);
        assertEquals(OrderException.alreadyClosed(), exception);
    }

    private static CreateOrderCommand createOrderCommand() {
        return CreateOrderCommand.builder()
                .userId(1L)
                .carId(2L)
                .dailyRentPrice(BigDecimal.ONE)
                .bookedDays(3)
                .build();
    }

}
