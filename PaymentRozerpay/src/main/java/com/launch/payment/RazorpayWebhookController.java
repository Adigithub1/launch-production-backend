package com.launch.payment;
import com.razorpay.Utils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/payments")
public class RazorpayWebhookController {
 private final PaymentService service; private final String secret;
 public RazorpayWebhookController(PaymentService service,@Value("${razorpay.webhook-secret}") String secret){this.service=service;this.secret=secret;}
 @PostMapping("/webhook") public ResponseEntity<Void> webhook(@RequestBody String raw,@RequestHeader("X-Razorpay-Signature") String signature){
  if(!Utils.verifyWebhookSignature(raw,signature,secret)) return ResponseEntity.status(401).build();
  JSONObject root=new JSONObject(raw); String event=root.getString("event");
  JSONObject payment=root.getJSONObject("payload").getJSONObject("payment").getJSONObject("entity");
  String orderId=payment.optString("order_id",null); String paymentId=payment.optString("id",null);
  if(orderId!=null){ if("payment.captured".equals(event)) service.markPaid(orderId,paymentId); else if("payment.failed".equals(event)) service.markFailed(orderId); }
  return ResponseEntity.ok().build();
 }
}
