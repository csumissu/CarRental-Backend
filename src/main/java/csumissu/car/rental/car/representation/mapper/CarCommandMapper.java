package csumissu.car.rental.car.representation.mapper;

import csumissu.car.rental.car.domain.command.CreateCarCommand;
import csumissu.car.rental.car.representation.dto.CreateCarRequest;
import org.mapstruct.Mapper;

@Mapper
public interface CarCommandMapper {

    CreateCarCommand toCreateCarCommand(CreateCarRequest request);

}
