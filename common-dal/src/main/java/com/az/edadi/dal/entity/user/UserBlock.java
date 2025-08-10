package com.az.edadi.dal.entity.user;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "user_block")
public class UserBlock {
    @Id
    private String id;
    private String blockerUserId;
    private String blockedUserId;
    private LocalDate blockedDate;
}
