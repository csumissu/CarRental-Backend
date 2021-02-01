package csumissu.car.rental.car.infrastructure.repository;

import csumissu.car.rental.car.domain.entity.Car;
import csumissu.car.rental.car.domain.entity.CarStatus;
import csumissu.car.rental.car.infrastructure.entity.CarEntity;
import csumissu.car.rental.car.infrastructure.repository.jpa.CarJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarRepositoryImplTest {

    @Mock
    private CarJpaRepository jpaRepository;
    @InjectMocks
    private CarRepositoryImpl repository;

    @Test
    public void should_save_car_entity() {
        // given
        var car = Car.builder()
                .status(CarStatus.AVAILABLE)
                .build();
        var captor = ArgumentCaptor.forClass(CarEntity.class);

        // when
        repository.save(car);

        // then
        verify(jpaRepository).save(captor.capture());
        assertEquals(captor.getValue().getId(), car.getId());
    }

    @Test
    public void should_find_one_car_by_id_when_car_exists() {
        // given
        var id = 1L;
        var carEntity = new CarEntity();
        carEntity.setId(id);
        carEntity.setStatus(CarStatus.AVAILABLE.getCode());
        when(jpaRepository.findById(id)).thenReturn(Optional.of(carEntity));

        // when
        var result = repository.findById(id);

        // then
        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
        assertEquals(CarStatus.AVAILABLE, result.get().getStatus());
    }
}
