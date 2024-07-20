package org.example.coupon;

import org.example.coupon.coupon.optimistic.OptimisticLockCoupon;
import org.example.coupon.coupon.optimistic.OptimisticLockCouponRepository;
import org.example.coupon.coupon.optimistic.OptimisticLockCouponService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@DisplayName("낙관적 락 테스트")
@SpringBootTest
public class OptimisticLockTest {

    @Autowired
    private OptimisticLockCouponService optimisticLockCouponService;

    @Autowired
    private OptimisticLockCouponRepository optimisticLockCouponRepository;

    @BeforeEach
    public void setUp() {
        optimisticLockCouponRepository.saveAndFlush(new OptimisticLockCoupon(1L, "테스트 쿠폰", 1000L, 0L, 0L));
    }


    @Test
    public void testIssueCoupon() throws InterruptedException {

        ExecutorService thread = Executors.newFixedThreadPool(32); // 32개 스레드 사용
        int numberOfAttempts = 30000; // 30000번의 쿠폰 발급 시도

        CountDownLatch endLatch = new CountDownLatch(numberOfAttempts);

        for (int i = 0; i < numberOfAttempts; i++) {
            thread.submit(() -> {
                try {
                    optimisticLockCouponService.issueCoupon(1L);
                } finally {
                    endLatch.countDown();
                }
            });
        }

        endLatch.await();

        long quantity = optimisticLockCouponService.getCouponQuantity(1L);

        assertEquals(0, quantity);
    }
}
