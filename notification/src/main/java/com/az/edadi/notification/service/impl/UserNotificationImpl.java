package com.az.edadi.notification.service.impl;

import com.az.edadi.common.util.AuthUtils;
import com.az.edadi.dal.repository.notification.NotificationRepository;
import com.az.edadi.model.response.notification.NotificationResponse;
import com.az.edadi.notification.service.UserNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserNotificationImpl implements UserNotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public Page<NotificationResponse> getUserNotifications(Pageable pageable) {
        var currentUserId = AuthUtils.getCurrentUserId();
        var notificationEntityPage = notificationRepository.findByUserId(currentUserId, pageable);
        var responseList = notificationEntityPage.map(n -> new NotificationResponse()).toList();
        return new PageImpl<>(responseList, pageable, notificationEntityPage.getTotalElements());
    }
}
