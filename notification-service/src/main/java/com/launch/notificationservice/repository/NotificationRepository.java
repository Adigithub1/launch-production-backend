package com.launch.notificationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.launch.notificationservice.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}