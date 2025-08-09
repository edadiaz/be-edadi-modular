package com.az.edadi.dal.entity.message;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "message")
public class Message {

    @Id
    private String id;
    private String conversationId;
    private String userId;
    private String text;
    private LocalDateTime createTs;

}
