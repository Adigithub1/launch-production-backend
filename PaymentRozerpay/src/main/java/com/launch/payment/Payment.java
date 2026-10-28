package com.launch.payment;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
@Entity @Table(name="payments")
public class Payment {
 @Id private UUID id;
 @Column(nullable=false) private UUID userId;
 @Column(nullable=false) private String planCode;
 @Column(nullable=false) private long amountPaise;
 @Column(nullable=false,length=3) private String currency;
 @Column(nullable=false,unique=true) private String receipt;
 @Column(nullable=false,unique=true) private String razorpayOrderId;
 @Column(unique=true) private String razorpayPaymentId;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private PaymentStatus status;
 @Column(nullable=false) private Instant createdAt;
 @Column(nullable=false) private Instant updatedAt;
 protected Payment() {}
 public Payment(UUID userId,String planCode,long amountPaise,String currency,String receipt,String orderId){
  this.id=UUID.randomUUID(); this.userId=userId; this.planCode=planCode; this.amountPaise=amountPaise;
  this.currency=currency; this.receipt=receipt; this.razorpayOrderId=orderId; this.status=PaymentStatus.CREATED;
  this.createdAt=Instant.now(); this.updatedAt=this.createdAt;
 }
 public void paid(String paymentId){this.razorpayPaymentId=paymentId;this.status=PaymentStatus.PAID;this.updatedAt=Instant.now();}
 public void failed(){this.status=PaymentStatus.FAILED;this.updatedAt=Instant.now();}
 public String getRazorpayOrderId(){return razorpayOrderId;} public String getRazorpayPaymentId(){return razorpayPaymentId;}
 public PaymentStatus getStatus(){return status;} public UUID getId(){return id;}
}
