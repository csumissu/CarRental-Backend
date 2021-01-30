package csumissu.car.rental.car.infrastructure.repository;

import csumissu.car.rental.car.domain.entity.Car;
import csumissu.car.rental.car.domain.repository.CarRepository;
import csumissu.car.rental.car.infrastructure.mapper.CarJpaMapper;
import csumissu.car.rental.car.infrastructure.repository.jpa.CarJpaRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CarRepositoryImpl implements CarRepository {

    private final CarJpaRepository jpaRepository;
    private final CarJpaMapper jpaMapper;

    public CarRepositoryImpl(CarJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
        this.jpaMapper = Mappers.getMapper(CarJpaMapper.class);
    }

    @Override
    @Transactional
    public void save(Car car) {
        var carEntity = jpaMapper.toCarEntity(car);
        jpaRepository.save(carEntity);
        car.setId(carEntity.getId());
    }

}
