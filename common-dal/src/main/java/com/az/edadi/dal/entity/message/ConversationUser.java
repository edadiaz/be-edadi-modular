package com.az.edadi.dal.entity.message;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "conversation_user")
public class ConversationUser {
    @Id
    private String id;
    private String conversationId;
    private String userId;
    private LocalDateTime updateTs;
    private LocalDateTime createTs;
}
