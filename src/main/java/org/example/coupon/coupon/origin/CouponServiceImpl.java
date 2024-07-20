package org.example.coupon.coupon.origin;

import lombok.RequiredArgsConstructor;
import org.example.coupon.coupon.pessimistic.PessimisticLockCoupon;
import org.example.coupon.coupon.pessimistic.PessimisticLockCouponRepository;
import org.example.coupon.coupon.pessimistic.PessimisticLockCouponService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    @Transactional
    @Override
    public void issueCoupon(Long couponSeq) {
        Coupon coupon = couponRepository.findById(couponSeq).orElseThrow(()-> new RuntimeException("쿠폰이 존재하지 않습니다."));
        coupon.issueCoupon();
        couponRepository.saveAndFlush(coupon); // 강제로 DB 업데이트
    }

    @Transactional(readOnly = true)
    @Override
    public long getCouponQuantity(Long couponSeq) {
        Coupon coupon = couponRepository.findById(couponSeq).orElseThrow(()-> new RuntimeException("쿠폰이 존재하지 않습니다."));
        return coupon.getQuantity();
    }

    @Transactional(readOnly = true)
    @Override
    public long getIssuedCouponQuantity(Long couponSeq) {
        Coupon coupon = couponRepository.findById(couponSeq).orElseThrow(()-> new RuntimeException("쿠폰이 존재하지 않습니다."));
        return coupon.getIssuedQuantity();
    }


}
