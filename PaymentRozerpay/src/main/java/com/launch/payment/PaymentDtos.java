package com.launch.payment;
import jakarta.validation.constraints.NotBlank;
public final class PaymentDtos {
 private PaymentDtos(){}
 public record CreateOrderRequest(@NotBlank String planCode){}
 public record CreateOrderResponse(String keyId,String orderId,long amount,String currency,String paymentReference){}
 public record VerifyRequest(String razorpayOrderId,String razorpayPaymentId,String razorpaySignature){}
 public record VerifyResponse(String status,String paymentReference){}
}
