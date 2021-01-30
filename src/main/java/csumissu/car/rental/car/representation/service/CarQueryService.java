package csumissu.car.rental.car.representation.service;

import csumissu.car.rental.car.exception.CarException;
import csumissu.car.rental.car.infrastructure.entity.CarEntity;
import csumissu.car.rental.car.infrastructure.repository.jpa.CarJpaRepository;
import csumissu.car.rental.car.representation.dto.CarDetailResponse;
import csumissu.car.rental.car.representation.dto.SearchCarRequest;
import csumissu.car.rental.car.representation.dto.SearchCarResponse;
import csumissu.car.rental.car.representation.mapper.CarQueryMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarQueryService {

    private final CarJpaRepository jpaRepository;
    private final CarQueryMapper queryMapper;

    public CarQueryService(CarJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
        this.queryMapper = Mappers.getMapper(CarQueryMapper.class);
    }

    @Transactional(readOnly = true)
    public CarDetailResponse getCarById(long id) {
        return jpaRepository.findById(id)
                .map(queryMapper::toCarDetailResponse)
                .orElseThrow(CarException::notFound);
    }

    @Transactional(readOnly = true)
    public Page<SearchCarResponse> searchCars(SearchCarRequest request, Pageable pageable) {
        var entity = new CarEntity();
        entity.setStatus(request.getStatus());
        var example = Example.of(entity);

        return jpaRepository.findAll(example, pageable)
                .map(queryMapper::toSearchCarResponse);
    }

}
