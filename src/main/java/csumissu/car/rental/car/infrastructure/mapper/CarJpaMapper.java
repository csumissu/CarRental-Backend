package csumissu.car.rental.car.infrastructure.mapper;

import csumissu.car.rental.car.domain.entity.Car;
import csumissu.car.rental.car.domain.entity.CarStatus;
import csumissu.car.rental.car.infrastructure.entity.CarEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public abstract class CarJpaMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    public abstract CarEntity toCarEntity(Car car);

    public abstract Car toCar(CarEntity carEntity);

    protected Integer toDatabaseCarStatus(CarStatus status) {
        return status.getCode();
    }

    protected CarStatus toDomainCarStatus(Integer code) {
        return CarStatus.of(code).orElse(null);
    }

}
