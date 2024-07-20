package org.example.coupon.coupon.pessimistic;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessimisticLockCouponRepository extends JpaRepository<PessimisticLockCoupon, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from PessimisticLockCoupon p where p.seq = :couponSeq")
    Optional<PessimisticLockCoupon> findByIdWithPessimisticLock(Long couponSeq);


}
