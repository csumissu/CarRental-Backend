package csumissu.car.rental.order.infrastructure.mapper;

import csumissu.car.rental.order.domain.entity.Order;
import csumissu.car.rental.order.infrastructure.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrderJpaMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    OrderEntity toOrderEntity(Order order);

    Order toOrder(OrderEntity orderEntity);

}
