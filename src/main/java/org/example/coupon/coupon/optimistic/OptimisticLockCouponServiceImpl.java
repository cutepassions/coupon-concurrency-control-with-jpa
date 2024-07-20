package org.example.coupon.coupon.optimistic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OptimisticLockCouponServiceImpl implements OptimisticLockCouponService {

    private final OptimisticLockCouponRepository optimisticLockCouponRepository;

    @Transactional
    @Override
    public void issueCoupon(Long couponSeq) {
        OptimisticLockCoupon optimisticLockCoupon = optimisticLockCouponRepository.findByIdWithOptimisticLock(couponSeq).orElseThrow(()-> new RuntimeException("쿠폰이 존재하지 않습니다."));
        optimisticLockCoupon.issueCoupon();
        optimisticLockCouponRepository.saveAndFlush(optimisticLockCoupon); // 강제로 DB 업데이트
    }

    @Transactional(readOnly = true)
    @Override
    public long getCouponQuantity(Long couponSeq) {
        OptimisticLockCoupon optimisticLockCoupon = optimisticLockCouponRepository.findById(couponSeq).orElseThrow(()-> new RuntimeException("쿠폰이 존재하지 않습니다."));
        return optimisticLockCoupon.getQuantity();
    }

    @Transactional(readOnly = true)
    @Override
    public long getIssuedCouponQuantity(Long couponSeq) {
        OptimisticLockCoupon optimisticLockCoupon = optimisticLockCouponRepository.findById(couponSeq).orElseThrow(()-> new RuntimeException("쿠폰이 존재하지 않습니다."));
        return optimisticLockCoupon.getIssuedQuantity();
    }


}
