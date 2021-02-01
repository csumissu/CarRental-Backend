package csumissu.car.rental.order.representation.mapper;

import csumissu.car.rental.car.infrastructure.entity.CarEntity;
import csumissu.car.rental.order.infrastructure.entity.OrderEntity;
import csumissu.car.rental.order.representation.dto.OrderDetailResponse;
import csumissu.car.rental.order.representation.dto.SearchOrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrderQueryMapper {

    SearchOrderResponse toSearchOrderResponse(OrderEntity orderEntity);

    @Mapping(target = "id", source = "orderEntity.id")
    @Mapping(target = "dailyRentPrice", source = "orderEntity.dailyRentPrice")
    @Mapping(target = "car", source = "carEntity")
    @Mapping(target = "user.id", source = "orderEntity.userId")
    OrderDetailResponse toOrderDetailResponse(OrderEntity orderEntity, CarEntity carEntity);

}
