package org.example.coupon.coupon.optimistic;

import jakarta.persistence.LockModeType;
import org.example.coupon.coupon.pessimistic.PessimisticLockCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OptimisticLockCouponRepository extends JpaRepository<OptimisticLockCoupon, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    @Query("select o from OptimisticLockCoupon o where o.seq = :couponSeq")
    Optional<OptimisticLockCoupon> findByIdWithOptimisticLock(Long couponSeq);
}
