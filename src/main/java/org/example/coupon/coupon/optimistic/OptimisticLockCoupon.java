package org.example.coupon.coupon.optimistic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class OptimisticLockCoupon {

    @Id
    @Comment("쿠폰 식별자")
    @Column(name = "optimistic_lock_coupon_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "optimistic_lock_coupon_name")
    @Comment("쿠폰 이름")
    private String name;

    @Column(name = "optimistic_lock_coupon_quantity")
    @Comment("쿠폰 수량")
    private Long quantity;

    @Column(name = "optimistic_lock_coupon_issued_quantity")
    @Comment("쿠폰 발급 수량")
    private Long issuedQuantity;

    @Version
    private Long version;


    // 쿠폰 발급
    public void issueCoupon() {
        if (this.quantity > 0) {
            this.quantity--;
            this.issuedQuantity++;
        }
    }

}
