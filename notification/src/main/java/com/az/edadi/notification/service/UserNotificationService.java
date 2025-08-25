package com.az.edadi.notification.service;

import com.az.edadi.model.response.notification.NotificationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserNotificationService {

     Page<NotificationResponse> getUserNotifications(Pageable pageable);
}
