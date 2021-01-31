package csumissu.car.rental.car.domain.repository;

import csumissu.car.rental.car.domain.entity.Car;

import java.util.Optional;

public interface CarRepository {

    void save(Car car);

    Optional<Car> findById(long id);

}
