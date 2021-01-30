package csumissu.car.rental.car.representation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarRequest {

    @NotBlank
    private String model;

    @DecimalMin(value = "0.00", inclusive = false)
    private BigDecimal dailyRentPrice;
}
