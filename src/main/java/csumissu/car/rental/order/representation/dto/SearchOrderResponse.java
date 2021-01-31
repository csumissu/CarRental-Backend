package csumissu.car.rental.order.representation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchOrderResponse {

    private Long id;

    private Long userId;

    private Long carId;

    private Instant bookedAt;

    private Instant returnedAt;

    private BigDecimal dailyRentPrice;

    private Integer bookedDays;

    private BigDecimal totalPrice;

}
