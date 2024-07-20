package org.example.coupon.coupon.optimistic;

public interface OptimisticLockCouponService {

    void issueCoupon(Long couponSeq);
    long getCouponQuantity(Long couponSeq);
    long getIssuedCouponQuantity(Long couponSeq);
}
