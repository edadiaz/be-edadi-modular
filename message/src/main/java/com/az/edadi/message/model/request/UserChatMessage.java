package com.az.edadi.message.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserChatMessage {
    private String conversationId;
    private String message;
}
