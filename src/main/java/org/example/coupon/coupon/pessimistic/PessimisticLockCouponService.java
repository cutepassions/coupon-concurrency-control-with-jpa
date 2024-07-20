package org.example.coupon.coupon.pessimistic;

public interface PessimisticLockCouponService {

    void issueCoupon(Long couponSeq);
    long getCouponQuantity(Long couponSeq);
    long getIssuedCouponQuantity(Long couponSeq);
}
