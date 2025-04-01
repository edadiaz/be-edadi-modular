package com.az.edadi.dal.entity.user;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "user_interest")
public class UserInterest {

    @Id
    private String id;
    private String userId;
    private String interestId;
}
