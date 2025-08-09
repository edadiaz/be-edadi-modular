package com.az.edadi.dal.entity.message;

import com.az.edadi.dal.types.ConversationType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "conversation")
public class Conversation {
    @Id
    private String id;
    private String name;
    private ConversationType type;
    private String photoUrl;
    private LocalDateTime createdDate;

}
