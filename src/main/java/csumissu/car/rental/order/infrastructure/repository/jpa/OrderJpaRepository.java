package csumissu.car.rental.order.infrastructure.repository.jpa;

import csumissu.car.rental.order.infrastructure.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long>, QuerydslPredicateExecutor<OrderEntity> {
}
