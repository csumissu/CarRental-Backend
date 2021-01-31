package csumissu.car.rental.order.domain.repository;

import csumissu.car.rental.order.domain.entity.Order;

import java.util.Optional;

public interface OrderRepository {

    void save(Order order);

    Optional<Order> findById(long id);

}
