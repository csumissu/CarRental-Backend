package csumissu.car.rental.order.representation.mapper;

import csumissu.car.rental.order.infrastructure.entity.OrderEntity;
import csumissu.car.rental.order.representation.dto.SearchOrderResponse;
import org.mapstruct.Mapper;

@Mapper
public interface OrderQueryMapper {

    SearchOrderResponse toSearchOrderResponse(OrderEntity orderEntity);

}
