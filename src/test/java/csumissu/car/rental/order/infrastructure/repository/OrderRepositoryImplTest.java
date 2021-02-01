package csumissu.car.rental.order.infrastructure.repository;

import csumissu.car.rental.order.domain.entity.Order;
import csumissu.car.rental.order.infrastructure.entity.OrderEntity;
import csumissu.car.rental.order.infrastructure.repository.jpa.OrderJpaRepository;
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
public class OrderRepositoryImplTest {

    @Mock
    private OrderJpaRepository jpaRepository;
    @InjectMocks
    private OrderRepositoryImpl repository;

    @Test
    public void should_save_order_entity() {
        // given
        var order = Order.builder()
                .build();
        var captor = ArgumentCaptor.forClass(OrderEntity.class);

        // when
        repository.save(order);

        // then
        verify(jpaRepository).save(captor.capture());
        assertEquals(captor.getValue().getId(), order.getId());
    }

    @Test
    public void should_find_one_order_by_id_when_order_exists() {
        // given
        var id = 1L;
        var orderEntity = new OrderEntity();
        orderEntity.setId(id);
        when(jpaRepository.findById(id)).thenReturn(Optional.of(orderEntity));

        // when
        var result = repository.findById(id);

        // then
        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
    }

    @Test
    public void should_find_nothing_by_id_when_order_not_exists() {
        // given
        var id = 1L;
        when(jpaRepository.findById(id)).thenReturn(Optional.empty());

        // when
        var result = repository.findById(id);

        // then
        assertTrue(result.isEmpty());
    }


}
