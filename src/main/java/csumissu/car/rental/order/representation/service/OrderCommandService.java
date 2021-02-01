package csumissu.car.rental.order.representation.service;

import csumissu.car.rental.car.domain.repository.CarRepository;
import csumissu.car.rental.car.exception.CarException;
import csumissu.car.rental.order.domain.command.CreateOrderCommand;
import csumissu.car.rental.order.domain.entity.Order;
import csumissu.car.rental.order.domain.repository.OrderRepository;
import csumissu.car.rental.order.exception.OrderException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderCommandService {

    private final OrderRepository orderRepository;
    private final CarRepository carRepository;

    public OrderCommandService(OrderRepository orderRepository, CarRepository carRepository) {
        this.orderRepository = orderRepository;
        this.carRepository = carRepository;
    }

    @Transactional
    public long createOrder(CreateOrderCommand command) {
        // TODO: replace with actual user id from token
        command.setUserId(1L);

        var car = carRepository.findById(command.getCarId())
                .orElseThrow(CarException::notFound);
        car.markAsInUse();
        carRepository.save(car);

        command.setDailyRentPrice(car.getDailyRentPrice());

        var order = Order.create(command);
        orderRepository.save(order);
        return order.getId();
    }

    @Transactional
    public void closeOrder(long id) {
        var order = orderRepository.findById(id)
                .orElseThrow(OrderException::notFound);
        var car = carRepository.findById(order.getCarId())
                .orElseThrow(CarException::notFound);

        // TODO: check only original user could close his order
        order.close();
        orderRepository.save(order);

        car.markAsAvailable();
        carRepository.save(car);
    }

}
