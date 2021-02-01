package csumissu.car.rental.car.representation.service;

import csumissu.car.rental.car.domain.command.CreateCarCommand;
import csumissu.car.rental.car.domain.entity.Car;
import csumissu.car.rental.car.domain.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CarCommandServiceTest {

    @Mock
    private CarRepository repository;
    @InjectMocks
    private CarCommandService service;

    @Test
    public void should_create_car() {
        // given
        var command = CreateCarCommand.builder()
                .model("any model")
                .dailyRentPrice(BigDecimal.ONE)
                .build();
        var captor = ArgumentCaptor.forClass(Car.class);

        // when
        var result = service.createCar(command);

        // then
        verify(repository).save(captor.capture());
        assertEquals(captor.getValue().getId(), result);
    }


}
