package csumissu.car.rental.car.domain.entity;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum CarStatus {

    AVAILABLE(1),
    IN_USE(2);

    private final int code;

    CarStatus(int code) {
        this.code = code;
    }

    public static Optional<CarStatus> of(int code) {
        return Arrays.stream(CarStatus.values())
                .filter(status -> status.getCode() == code)
                .findFirst();
    }

}
