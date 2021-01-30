package csumissu.car.rental.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.RoundingMode;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    public static final int BASE_SCALE = 2;
    public static final RoundingMode BASE_ROUNDING_MODE = RoundingMode.HALF_UP;

}
