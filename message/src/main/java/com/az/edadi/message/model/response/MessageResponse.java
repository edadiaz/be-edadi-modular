package com.az.edadi.message.model.response;

import com.az.edadi.model.response.user.UserSummaryResponse;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageResponse {
    private String id;
    private String message;
    private String conversationId;
    private LocalDateTime messageTime;
    private UserSummaryResponse sender;
}
