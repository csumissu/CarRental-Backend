package csumissu.car.rental.car.representation.mapper;

import csumissu.car.rental.car.infrastructure.entity.CarEntity;
import csumissu.car.rental.car.representation.dto.CarDetailResponse;
import csumissu.car.rental.car.representation.dto.SearchCarResponse;
import org.mapstruct.Mapper;

@Mapper
public interface CarQueryMapper {

    CarDetailResponse toCarDetailResponse(CarEntity carEntity);

    SearchCarResponse toSearchCarResponse(CarEntity carEntity);

}
