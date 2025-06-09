package com.az.edadi.notification.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notification")
public class UserNotificationController {
    @GetMapping("/me")
    String getUserNotifications() {
        return "User notifications";
    }
}
