package csumissu.car.rental.car.infrastructure.repository.jpa;

import csumissu.car.rental.car.infrastructure.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarJpaRepository extends JpaRepository<CarEntity, Long> {
}
