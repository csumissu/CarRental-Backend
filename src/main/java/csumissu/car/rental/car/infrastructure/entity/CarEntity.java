package csumissu.car.rental.car.infrastructure.entity;

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

@Getter
@Setter
@Entity
@Table(name = "car")
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE car SET deleted = true, version = version + 1, updated_at = current_timestamp " +
        "WHERE id = ? AND version = ?", check = ResultCheckStyle.COUNT)
public class CarEntity extends BaseEntity {

    @Column
    private String model;

    @Column
    private BigDecimal dailyRentPrice;

    @Column
    private Integer status;

}
