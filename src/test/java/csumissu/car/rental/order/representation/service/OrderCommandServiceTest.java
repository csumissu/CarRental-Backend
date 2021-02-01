package csumissu.car.rental.order.representation.service;

import csumissu.car.rental.car.domain.entity.Car;
import csumissu.car.rental.car.domain.repository.CarRepository;
import csumissu.car.rental.car.exception.CarException;
import csumissu.car.rental.order.domain.command.CreateOrderCommand;
import csumissu.car.rental.order.domain.entity.Order;
import csumissu.car.rental.order.domain.repository.OrderRepository;
import csumissu.car.rental.order.exception.OrderException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderCommandServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private CarRepository carRepository;
    @InjectMocks
    private OrderCommandService service;

    @Test
    public void should_create_order_when_command_is_correct() {
        // given
        var command = createOrderCommand();
        var car = mock(Car.class);
        when(car.getDailyRentPrice()).thenReturn(BigDecimal.TEN);
        when(carRepository.findById(command.getCarId())).thenReturn(Optional.of(car));
        var captor = ArgumentCaptor.forClass(Order.class);

        // when
        var result = service.createOrder(command);

        // then
        verify(orderRepository).save(captor.capture());
        assertEquals(captor.getValue().getId(), result);

        verify(car).markAsInUse();
        verify(carRepository).save(car);
    }

    @Test
    public void should_create_order_failed_when_car_not_exists() {
        // given
        var command = createOrderCommand();
        when(carRepository.findById(command.getCarId())).thenReturn(Optional.empty());

        // when
        Executable action = () -> service.createOrder(command);

        // then
        var exception = assertThrows(CarException.class, action);
        assertEquals(CarException.notFound(), exception);

        verify(orderRepository, never()).save(any(Order.class));
        verify(carRepository, never()).save(any(Car.class));
    }

    @Test
    public void should_close_order_when_id_is_correct() {
        // given
        var id = 1L;
        var order = mock(Order.class);
        when(order.getCarId()).thenReturn(id);
        when(orderRepository.findById(id)).thenReturn(Optional.of(order));
        var car = mock(Car.class);
        when(carRepository.findById(id)).thenReturn(Optional.of(car));

        // when
        service.closeOrder(id);

        // then
        verify(order).close();
        verify(orderRepository).save(order);

        verify(car).markAsAvailable();
        verify(carRepository).save(car);
    }

    @Test
    public void should_close_order_failed_when_order_not_exists() {
        // given
        var id = 1L;
        when(orderRepository.findById(id)).thenReturn(Optional.empty());

        // when
        Executable action = () -> service.closeOrder(id);

        // then
        var exception = assertThrows(OrderException.class, action);
        assertEquals(OrderException.notFound(), exception);

        verify(orderRepository, never()).save(any(Order.class));
        verify(carRepository, never()).save(any(Car.class));
    }

    @Test
    public void should_close_order_failed_when_car_not_exists() {
        // given
        var id = 1L;
        var order = mock(Order.class);
        when(order.getCarId()).thenReturn(id);
        when(orderRepository.findById(id)).thenReturn(Optional.of(order));
        when(carRepository.findById(id)).thenReturn(Optional.empty());

        // when
        Executable action = () -> service.closeOrder(id);

        // then
        var exception = assertThrows(CarException.class, action);
        assertEquals(CarException.notFound(), exception);

        verify(orderRepository, never()).save(any(Order.class));
        verify(carRepository, never()).save(any(Car.class));
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
