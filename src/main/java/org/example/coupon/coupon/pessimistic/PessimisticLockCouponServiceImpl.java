package org.example.coupon.coupon.pessimistic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PessimisticLockCouponServiceImpl implements PessimisticLockCouponService {

    private final PessimisticLockCouponRepository pessimisticLockCouponRepository;

    @Transactional
    @Override
    public void issueCoupon(Long couponSeq) {
        PessimisticLockCoupon pessimisticLockCoupon = pessimisticLockCouponRepository.findByIdWithPessimisticLock(couponSeq).orElseThrow(()-> new RuntimeException("쿠폰이 존재하지 않습니다."));
        pessimisticLockCoupon.issueCoupon();
        pessimisticLockCouponRepository.saveAndFlush(pessimisticLockCoupon); // 강제로 DB 업데이트
    }

    @Transactional(readOnly = true)
    @Override
    public long getCouponQuantity(Long couponSeq) {
        PessimisticLockCoupon pessimisticLockCoupon = pessimisticLockCouponRepository.findById(couponSeq).orElseThrow(()-> new RuntimeException("쿠폰이 존재하지 않습니다."));
        return pessimisticLockCoupon.getQuantity();
    }

    @Transactional(readOnly = true)
    @Override
    public long getIssuedCouponQuantity(Long couponSeq) {
        PessimisticLockCoupon pessimisticLockCoupon = pessimisticLockCouponRepository.findById(couponSeq).orElseThrow(()-> new RuntimeException("쿠폰이 존재하지 않습니다."));
        return pessimisticLockCoupon.getIssuedQuantity();
    }


}
