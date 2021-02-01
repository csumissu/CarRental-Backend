package csumissu.car.rental.order.infrastructure.entity;

import com.querydsl.core.annotations.QueryEntity;
import csumissu.car.rental.common.data.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@QueryEntity
@Table(name = "t_order")
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE t_order SET deleted = true, version = version + 1, updated_at = current_timestamp " +
        "WHERE id = ? AND version = ?", check = ResultCheckStyle.COUNT)
public class OrderEntity extends BaseEntity {

    @Column
    private Long userId;

    @Column
    private Long carId;

    @Column
    private Instant bookedAt;

    @Column
    private Instant returnedAt;

    @Column
    private BigDecimal dailyRentPrice;

    @Column
    private Integer bookedDays;

    @Column
    private BigDecimal totalPrice;

}
