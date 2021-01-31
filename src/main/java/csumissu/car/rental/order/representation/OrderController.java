package csumissu.car.rental.order.representation;

import csumissu.car.rental.common.response.UseCustomizedResponse;
import csumissu.car.rental.order.representation.dto.CreateOrderRequest;
import csumissu.car.rental.order.representation.dto.CreateOrderResponse;
import csumissu.car.rental.order.representation.dto.OrderDetailResponse;
import csumissu.car.rental.order.representation.dto.SearchOrderRequest;
import csumissu.car.rental.order.representation.dto.SearchOrderResponse;
import csumissu.car.rental.order.representation.mapper.OrderCommandMapper;
import csumissu.car.rental.order.representation.service.OrderCommandService;
import csumissu.car.rental.order.representation.service.OrderQueryService;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@UseCustomizedResponse
@RequestMapping("/orders")
public class OrderController {

    private final OrderCommandService commandService;
    private final OrderCommandMapper commandMapper;
    private final OrderQueryService queryService;

    public OrderController(OrderCommandService commandService, OrderQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
        this.commandMapper = Mappers.getMapper(OrderCommandMapper.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateOrderResponse createOrder(@RequestBody @Valid CreateOrderRequest request) {
        var command = commandMapper.toCreateOrderCommand(request);
        var id = commandService.createOrder(command);
        return new CreateOrderResponse(id);
    }

    @PostMapping("/{id}/close")
    public void closeOrder(@PathVariable long id) {
        commandService.closeOrder(id);
    }

    @GetMapping("/{id}")
    public OrderDetailResponse getOrder(@PathVariable long id) {
        return queryService.getOrderById(id);
    }

    @GetMapping
    public Page<SearchOrderResponse> searchOrders(SearchOrderRequest request,
                                                  @PageableDefault(size = 20) Pageable pageable) {
        return queryService.searchOrders(request, pageable);
    }

}
