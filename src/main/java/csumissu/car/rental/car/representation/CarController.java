package csumissu.car.rental.car.representation;

import csumissu.car.rental.car.representation.dto.CarDetailResponse;
import csumissu.car.rental.car.representation.dto.CreateCarRequest;
import csumissu.car.rental.car.representation.dto.CreateCarResponse;
import csumissu.car.rental.car.representation.dto.SearchCarRequest;
import csumissu.car.rental.car.representation.dto.SearchCarResponse;
import csumissu.car.rental.car.representation.mapper.CarCommandMapper;
import csumissu.car.rental.car.representation.service.CarCommandService;
import csumissu.car.rental.car.representation.service.CarQueryService;
import csumissu.car.rental.common.response.UseCustomizedResponse;
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
@RequestMapping("/cars")
public class CarController {

    private final CarCommandService commandService;
    private final CarCommandMapper commandMapper;
    private final CarQueryService queryService;

    public CarController(CarCommandService commandService, CarQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
        this.commandMapper = Mappers.getMapper(CarCommandMapper.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCarResponse createCar(@RequestBody @Valid CreateCarRequest request) {
        var command = commandMapper.toCreateCarCommand(request);
        var id = commandService.createCar(command);
        return new CreateCarResponse(id);
    }

    @GetMapping
    @RequestMapping("/{id}")
    public CarDetailResponse getCar(@PathVariable long id) {
        return queryService.getCarById(id);
    }

    @GetMapping
    public Page<SearchCarResponse> searchCars(SearchCarRequest request,
                                              @PageableDefault(size = 20) Pageable pageable) {
        return queryService.searchCars(request, pageable);
    }

}
