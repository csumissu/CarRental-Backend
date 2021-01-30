package csumissu.car.rental.car.representation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDetailResponse {

    private Long id;

    private String model;

    private BigDecimal dailyRentPrice;

    private Integer status;

}
