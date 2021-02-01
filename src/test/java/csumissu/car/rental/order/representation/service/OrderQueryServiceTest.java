package csumissu.car.rental.order.representation.service;

import csumissu.car.rental.order.infrastructure.entity.OrderEntity;
import csumissu.car.rental.order.infrastructure.repository.jpa.OrderJpaRepository;
import csumissu.car.rental.order.representation.dto.SearchOrderRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.spockframework.util.CollectionUtil.listOf;

@ExtendWith(MockitoExtension.class)
public class OrderQueryServiceTest {

    @Mock
    private OrderJpaRepository jpaRepository;
    @Mock
    private EntityManager entityManager;
    @InjectMocks
    private OrderQueryService service;

    @Test
    public void should_search_cars_by_request() {
        // given
        var request = SearchOrderRequest.builder()
                .userId(1L)
                .build();
        var pageable = PageRequest.of(0, 10);
        var orderEntity = orderEntity(1L);
        var pagedResult = new PageImpl<>(listOf(orderEntity));
        when(jpaRepository.findAll(any(), eq(pageable))).thenReturn(pagedResult);

        // when
        var result = service.searchOrders(request, pageable);

        // then
        assertNotNull(result);
        assertEquals(pagedResult.getNumberOfElements(), result.getNumberOfElements());
        assertEquals(pagedResult.getContent().size(), result.getContent().size());
        assertEquals(orderEntity.getId(), result.getContent().get(0).getId());
    }

    private static OrderEntity orderEntity(long id) {
        var orderEntity = new OrderEntity();
        orderEntity.setId(id);
        return orderEntity;
    }


}
