package com.launch.authservice.mapper;

import org.springframework.stereotype.Component;

import com.launch.authservice.dto.request.UserRequest;
import com.launch.authservice.dto.response.UserResponse;
import com.launch.authservice.entity.User;

@Component
public class UserMapper {

    public User toEntity(UserRequest x) {

        User user = new User();

        user.setEmail(x.getEmail());
        user.setPasswordHash(x.getPasswordHash());
        user.setRole(x.getRole());
        user.setStatus(x.getStatus());
        user.setDeviceId(x.getDeviceId());

        return user;
    }

    public void update(User e, UserRequest x) {

        e.setEmail(x.getEmail());
        e.setPasswordHash(x.getPasswordHash());
        e.setRole(x.getRole());
        e.setStatus(x.getStatus());
        e.setDeviceId(x.getDeviceId());
    }

    public UserResponse toResponse(User user) {

        UserResponse response =
                new UserResponse();

        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setStatus(user.getStatus());
        response.setEmailVerified(
                user.isEmailVerified()
        );

        return response;
    }
}