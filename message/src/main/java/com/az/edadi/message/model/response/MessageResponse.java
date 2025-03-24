package com.az.edadi.message.model.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageResponse {
    private String messageId;
    private String message;
    private String senderId;
    private String conversationId;
    private LocalDateTime messageTime;
}
