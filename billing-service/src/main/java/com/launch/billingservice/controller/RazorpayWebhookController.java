package com.launch.billingservice.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.launch.billingservice.service.PaymentService;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

@RestController
@RequestMapping("/api/payments")
public class RazorpayWebhookController {

    private final PaymentService service;
    private final String webhookSecret;

    public RazorpayWebhookController(
            PaymentService service,
            @Value("${razorpay.webhook-secret}") String webhookSecret) {

        this.service = service;
        this.webhookSecret = webhookSecret;
    }

    @PostMapping("/webhook")
    public ResponseEntity<Void> webhook(
            @RequestBody String rawBody,
            @RequestHeader("X-Razorpay-Signature") String signature) throws RazorpayException {

        boolean valid = Utils.verifyWebhookSignature(
                rawBody,
                signature,
                webhookSecret
        );

        if (!valid) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
        }

        JSONObject root = new JSONObject(rawBody);

        String event = root.optString("event");

        JSONObject payment = root
                .getJSONObject("payload")
                .getJSONObject("payment")
                .getJSONObject("entity");

        String orderId =
                payment.optString("order_id", null);

        String paymentId =
                payment.optString("id", null);

        if (orderId != null) {

            if ("payment.captured".equals(event)) {

                service.captured(
                        orderId,
                        paymentId
                );

            } else if ("payment.failed".equals(event)) {

                service.failed(orderId);
            }
        }

        return ResponseEntity.ok().build();
    }
}