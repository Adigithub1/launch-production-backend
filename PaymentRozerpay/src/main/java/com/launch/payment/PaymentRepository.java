package com.launch.payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface PaymentRepository extends JpaRepository<Payment,UUID>{
 Optional<Payment> findByRazorpayOrderId(String id);
 Optional<Payment> findByRazorpayPaymentId(String id);
}
