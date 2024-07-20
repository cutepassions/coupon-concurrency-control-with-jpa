package org.example.coupon.coupon.origin;

public interface CouponService {

    void issueCoupon(Long couponSeq);
    long getCouponQuantity(Long couponSeq);
    long getIssuedCouponQuantity(Long couponSeq);
}
