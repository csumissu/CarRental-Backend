package csumissu.car.rental.order.representation.mapper;

import csumissu.car.rental.order.domain.command.CreateOrderCommand;
import csumissu.car.rental.order.representation.dto.CreateOrderRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrderCommandMapper {

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "dailyRentPrice", ignore = true)
    CreateOrderCommand toCreateOrderCommand(CreateOrderRequest request);

}
