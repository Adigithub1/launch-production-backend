package com.launch.payment;
import com.razorpay.RazorpayException;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
@RestController @RequestMapping("/api/payments")
public class PaymentController {
 private final PaymentService service;
 public PaymentController(PaymentService service){this.service=service;}
 @PostMapping("/orders") public PaymentDtos.CreateOrderResponse create(@Valid @RequestBody PaymentDtos.CreateOrderRequest r,Authentication auth) throws RazorpayException {
  UUID userId=UUID.fromString(auth.getName()); // adapt if JWT subject is email
  return service.create(userId,r.planCode());
 }
 @PostMapping("/verify") public PaymentDtos.VerifyResponse verify(@RequestBody PaymentDtos.VerifyRequest r){ return service.verify(r); }
}
