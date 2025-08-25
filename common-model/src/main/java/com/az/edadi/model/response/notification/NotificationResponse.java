package com.az.edadi.model.response.notification;

import com.az.edadi.model.response.user.UserSummaryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponse {
    private String id;
    private String title;
    private String body;
    private LocalDateTime createdAt;
    private String link;
    private UserSummaryResponse user;

}
