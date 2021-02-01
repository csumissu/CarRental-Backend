package csumissu.car.rental.car.representation.service;

import csumissu.car.rental.car.domain.entity.CarStatus;
import csumissu.car.rental.car.exception.CarException;
import csumissu.car.rental.car.infrastructure.entity.CarEntity;
import csumissu.car.rental.car.infrastructure.repository.jpa.CarJpaRepository;
import csumissu.car.rental.car.representation.dto.SearchCarRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.spockframework.util.CollectionUtil.listOf;

@ExtendWith(MockitoExtension.class)
public class CarQueryServiceTest {

    @Mock
    private CarJpaRepository jpaRepository;
    @InjectMocks
    private CarQueryService service;

    @Test
    public void should_return_car_detail_when_id_exists() {
        // given
        var id = 1L;
        var carEntity = carEntity(id);
        when(jpaRepository.findById(id)).thenReturn(Optional.of(carEntity));

        // when
        var result = service.getCarById(id);

        // then
        assertNotNull(result);
        assertEquals(carEntity.getId(), result.getId());
        assertEquals(carEntity.getModel(), result.getModel());
        assertEquals(carEntity.getStatus(), result.getStatus());
        assertEquals(carEntity.getDailyRentPrice(), result.getDailyRentPrice());
    }

    @Test
    public void should_throw_car_not_found_exception_when_id_not_exists() {
        // given
        var id = 1L;
        when(jpaRepository.findById(id)).thenReturn(Optional.empty());

        // when
        Executable action = () -> service.getCarById(id);

        // then
        var exception = assertThrows(CarException.class, action);
        assertEquals(CarException.notFound(), exception);
    }

    @Test
    public void should_search_cars_by_request() {
        // given
        var request = SearchCarRequest.builder()
                .status(CarStatus.IN_USE.getCode())
                .build();
        var pageable = PageRequest.of(0, 10);
        var carEntity = carEntity(1L);
        var pagedResult = new PageImpl<>(listOf(carEntity));
        when(jpaRepository.findAll(any(), eq(pageable))).thenReturn(pagedResult);

        // when
        var result = service.searchCars(request, pageable);

        // then
        assertNotNull(result);
        assertEquals(pagedResult.getNumberOfElements(), result.getNumberOfElements());
        assertEquals(pagedResult.getContent().size(), result.getContent().size());
        assertEquals(carEntity.getId(), result.getContent().get(0).getId());
    }

    private static CarEntity carEntity(long id) {
        var carEntity = new CarEntity();
        carEntity.setId(id);
        carEntity.setModel("any model");
        carEntity.setDailyRentPrice(BigDecimal.TEN);
        carEntity.setStatus(CarStatus.IN_USE.getCode());
        return carEntity;
    }

}
