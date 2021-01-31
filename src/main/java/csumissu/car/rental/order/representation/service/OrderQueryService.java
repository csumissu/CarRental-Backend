package csumissu.car.rental.order.representation.service;

import csumissu.car.rental.order.infrastructure.entity.OrderEntity;
import csumissu.car.rental.order.infrastructure.repository.jpa.OrderJpaRepository;
import csumissu.car.rental.order.representation.dto.OrderDetailResponse;
import csumissu.car.rental.order.representation.dto.SearchOrderRequest;
import csumissu.car.rental.order.representation.dto.SearchOrderResponse;
import csumissu.car.rental.order.representation.mapper.OrderQueryMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderQueryService {

    private final OrderJpaRepository jpaRepository;
    private final OrderQueryMapper queryMapper;

    public OrderQueryService(OrderJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
        this.queryMapper = Mappers.getMapper(OrderQueryMapper.class);
    }

    @Transactional(readOnly = true)
    public OrderDetailResponse getOrderById(long id) {
        return null;
    }

    @Transactional(readOnly = true)
    public Page<SearchOrderResponse> searchOrders(SearchOrderRequest request, Pageable pageable) {
        var entity = new OrderEntity();
        entity.setUserId(request.getUserId());
        var example = Example.of(entity);

        return jpaRepository.findAll(example, pageable)
                .map(queryMapper::toSearchOrderResponse);
    }

}
