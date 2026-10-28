package com.launch.billingservice.mapper;

import org.springframework.stereotype.Component;

import com.launch.billingservice.dto.request.SubscriptionRequest;
import com.launch.billingservice.dto.response.SubscriptionResponse;
import com.launch.billingservice.entity.Subscription;

@Component
public class SubscriptionMapper {

    public Subscription toEntity(SubscriptionRequest x) {

        return Subscription.builder()
                .userId(x.getUserId())
                .plan(x.getPlan())
                .status(x.getStatus())
                .build();
    }

    public void update(
            Subscription e,
            SubscriptionRequest x) {

        e.setUserId(x.getUserId());
        e.setPlan(x.getPlan());
        e.setStatus(x.getStatus());
    }

    public SubscriptionResponse toResponse(Subscription x) {

        return SubscriptionResponse.builder()
                .id(x.getId())
                .userId(x.getUserId())
                .plan(x.getPlan())
                .status(x.getStatus())
                .build();
    }
}