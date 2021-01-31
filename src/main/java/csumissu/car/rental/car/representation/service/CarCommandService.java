package csumissu.car.rental.car.representation.service;

import csumissu.car.rental.car.domain.command.CreateCarCommand;
import csumissu.car.rental.car.domain.entity.Car;
import csumissu.car.rental.car.domain.repository.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarCommandService {

    private final CarRepository carRepository;

    public CarCommandService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Transactional
    public long createCar(CreateCarCommand command) {
        var car = Car.create(command);
        carRepository.save(car);
        return car.getId();
    }

}
