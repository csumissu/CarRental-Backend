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
public class OrderDetailResponse {

    private Long id;

    private Instant bookedAt;

    private Instant returnedAt;

    private BigDecimal dailyRentPrice;

    private Integer bookedDays;

    private BigDecimal totalPrice;

    private CarResponse car;

    private UserResponse user;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CarResponse {

        private Long id;

        private String model;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserResponse {

        private Long id;

        private String nickName;

    }

}
