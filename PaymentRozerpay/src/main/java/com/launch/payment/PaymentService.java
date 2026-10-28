package com.launch.payment;
import com.razorpay.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
@Service
public class PaymentService {
 private final RazorpayClient client; private final PaymentRepository repo; private final String keyId; private final String keySecret;
 public PaymentService(PaymentRepository repo,@Value("${razorpay.key-id}") String keyId,@Value("${razorpay.key-secret}") String keySecret) throws RazorpayException {
  this.repo=repo; this.keyId=keyId; this.keySecret=keySecret; this.client=new RazorpayClient(keyId,keySecret);
 }
 private long price(String plan){ return switch(plan){case "MONTHLY" -> 49900L; case "YEARLY" -> 399900L; default -> throw new IllegalArgumentException("Invalid plan");}; }
 @Transactional
 public PaymentDtos.CreateOrderResponse create(UUID userId,String plan) throws RazorpayException {
  long amount=price(plan); String receipt="launch_"+UUID.randomUUID().toString().replace("-","").substring(0,20);
  JSONObject req=new JSONObject().put("amount",amount).put("currency","INR").put("receipt",receipt);
  Order order=client.orders.create(req);
  Payment p=repo.save(new Payment(userId,plan,amount,"INR",receipt,order.get("id")));
  return new PaymentDtos.CreateOrderResponse(keyId,p.getRazorpayOrderId(),amount,"INR",p.getId().toString());
 }
 @Transactional
 public PaymentDtos.VerifyResponse verify(PaymentDtos.VerifyRequest r){
  JSONObject a=new JSONObject().put("razorpay_order_id",r.razorpayOrderId()).put("razorpay_payment_id",r.razorpayPaymentId()).put("razorpay_signature",r.razorpaySignature());
  if(!Utils.verifyPaymentSignature(a,keySecret)) throw new SecurityException("Invalid payment signature");
  Payment p=repo.findByRazorpayOrderId(r.razorpayOrderId()).orElseThrow();
  p.paid(r.razorpayPaymentId());
  return new PaymentDtos.VerifyResponse("PAID",p.getId().toString());
 }
 @Transactional public void markPaid(String orderId,String paymentId){ Payment p=repo.findByRazorpayOrderId(orderId).orElseThrow(); if(p.getStatus()!=PaymentStatus.PAID)p.paid(paymentId); }
 @Transactional public void markFailed(String orderId){ repo.findByRazorpayOrderId(orderId).ifPresent(Payment::failed); }
}
