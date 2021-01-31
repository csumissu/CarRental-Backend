package csumissu.car.rental.order.infrastructure.repository;

import csumissu.car.rental.order.domain.entity.Order;
import csumissu.car.rental.order.domain.repository.OrderRepository;
import csumissu.car.rental.order.infrastructure.mapper.OrderJpaMapper;
import csumissu.car.rental.order.infrastructure.repository.jpa.OrderJpaRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository jpaRepository;
    private final OrderJpaMapper jpaMapper;

    public OrderRepositoryImpl(OrderJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
        this.jpaMapper = Mappers.getMapper(OrderJpaMapper.class);
    }

    @Override
    public void save(Order order) {
        var orderEntity = jpaMapper.toOrderEntity(order);
        jpaRepository.save(orderEntity);
        order.setId(orderEntity.getId());
    }

    @Override
    public Optional<Order> findById(long id) {
        return jpaRepository.findById(id)
                .map(jpaMapper::toOrder);
    }
}
