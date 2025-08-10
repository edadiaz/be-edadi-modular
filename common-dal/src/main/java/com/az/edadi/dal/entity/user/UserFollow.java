package com.az.edadi.dal.entity.user;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "user_follow")
public class UserFollow {
    @Id
    private String id;
    private String followerId;
    private String followeeId;
    private String followingId;
    private LocalDate followingDate;
}
