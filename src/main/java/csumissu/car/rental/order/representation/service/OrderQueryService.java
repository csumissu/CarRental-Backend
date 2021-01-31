package csumissu.car.rental.order.representation.service;

import com.querydsl.jpa.impl.JPAQuery;
import csumissu.car.rental.order.exception.OrderException;
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

import javax.persistence.EntityManager;
import java.util.Optional;

import static csumissu.car.rental.car.infrastructure.entity.QCarEntity.carEntity;
import static csumissu.car.rental.order.infrastructure.entity.QOrderEntity.orderEntity;

@Service
public class OrderQueryService {

    private final OrderJpaRepository jpaRepository;
    private final OrderQueryMapper queryMapper;
    private final EntityManager entityManager;

    public OrderQueryService(OrderJpaRepository jpaRepository, EntityManager entityManager) {
        this.jpaRepository = jpaRepository;
        this.queryMapper = Mappers.getMapper(OrderQueryMapper.class);
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public OrderDetailResponse getOrderById(long id) {
        var jpaQuery = new JPAQuery<>(entityManager);
        var result = jpaQuery.select(orderEntity, carEntity)
                .from(orderEntity)
                .innerJoin(carEntity)
                .on(carEntity.id.eq(orderEntity.carId))
                .where(orderEntity.id.eq(id))
                .fetchOne();

        return Optional.ofNullable(result)
                .map(tuple -> queryMapper.toOrderDetailResponse(tuple.get(orderEntity), tuple.get(carEntity)))
                .orElseThrow(OrderException::notFound);
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
